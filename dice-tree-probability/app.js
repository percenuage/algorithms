const mathjs = require('mathjs');
const _ = require('lodash');

console.log('DICE PROBABILITY');

const fixed = (value, digits = 2) => parseFloat(value.toFixed(digits));

const generate_all_combinations = (sided = 6, dices) => {
  const nb_combination = sided ** dices;
  const combinations = [];
  let roll = Array(dices).fill(1);
  for (let i = 0; i < nb_combination; i++) {
    combinations.push([...roll]);
    increment_combination(roll, sided);
  }
  return combinations;
}

const increment_combination = (roll, sided) => {
  let index = 0;
  while (roll[index] + 1 > sided) {
    roll[index] = 1;
    index++;
  }
  roll[index]++;
}

const generate_occurrences = combinations => {
  const occurrences = {};
  combinations.forEach(comb => {
    const count = _.countBy(comb);
    const id = get_poker_combination(count);
    occurrences[id] ? occurrences[id]++ : occurrences[id] = 1;
  })
  occurrences['all'] = combinations.length;
  return occurrences;
}

const get_poker_combination = count => {
  let id = '';
  if (is_large_straight(count))
    id += 'large-straight+'
  else if (is_small_straight(count))
    id += 'small-straight+'
  _.each(count, (value) => {
    if (value === 2)
      id += 'pair+'
    else if (value === 3)
      id += 'brelan+'
    else if (value === 4)
      id += 'four+'
    else if (value === 5)
      id += 'five+'
    else if (value > 5)
      id += value
    id = id.replace(/brelan\+pair|pair\+brelan/g, 'full');
  })
  return id.slice(0, -1) || 'NA';
}

const is_large_straight = count =>
  [1, 2, 3, 4, 5].every(i => count[i]) || [2, 3, 4, 5, 6].every(i => count[i]);

const is_small_straight = count =>
  [1, 2, 3, 4].every(i => count[i]) || [2, 3, 4, 5].every(i => count[i]) || [3, 4, 5, 6].every(i => count[i]);

const generate_probabilities = occurrences => {
  const total = occurrences['all'];
  return _.mapValues(occurrences, value => fixed(value / total));
}

// -------------------

const tree = parent => {
  const { k, n, roll } = parent;
  if (roll > rolls) return 0;
  if (is_success_roll(k, n)) {
    nodes.push(parent);
    return 0;
  }
  const child_nodes = generate_child_nodes(parent);
  _.each(child_nodes, tree)
}

const generate_child_nodes = parent => {
  const { k, n, roll } = parent;
  const child_nodes = [];

  const child_sided = has_all_sides(k, n) ? sided : 1;
  const start = has_all_sides(k, n) ? 2 : 1;

  const fail_node = generate_node(0, n - k, child_sided, roll + 1, parent)

  child_nodes.push(fail_node);
  for (let i = start; i <= n - k; i++) {
    const node = generate_node(i, n - k, child_sided, roll + 1, parent);
    child_nodes.push(node);
  }
  return child_nodes;
}

const generate_node = (k, n, s, roll, parent) => {
  return {
    k, n, s, roll, parent,
    p: calc_probability_by_roll(k, n, s, roll),
  };
}

const has_all_sides = (k, n) => k === 0 && n === dices;

const is_success_roll = (k, n) => k === n && !is_failed_roll(k);

const is_failed_roll = k => k === 0;

const get_node_path = node => {
  if (!node.parent) return '[*]'
  const { k, n, s, p } = node;
  return `[${k}|${n}|${s}]${fixed(p*100, 1)} > ${get_node_path(node.parent)}`;
}

const get_node_probability = node => {
  if (!node.parent) return 1;
  return node.p * get_node_probability(node.parent);
}

const calc_tree_probability = nodes => {
  const probabilities = _.map(nodes, get_node_probability);
  return fixed(_.sum(probabilities), 3);
}

const print_nodes_by = (nodes, fn) => {
  const node_paths = _.map(nodes, fn);
  console.log(node_paths);
}

const calc_probability_by_roll = (k, n, s, roll) => {
  if (roll === 0) return 1;
  const k_inverse = has_all_sides(k, n) ? 2 : 1
  return is_failed_roll(k) ? calc_probability_inverse(k_inverse, n, s) : calc_probability(k, n, s);
}

const calc_probability = (k, n, s) => {
  const release_combinations = (sided - 1) ** (n - k);
  let success = s * mathjs.combinations(n, k) * release_combinations;
  if (k === 2 && n === 5 && s === 6) success = 5400; // pair + double pair
  return success / (sided ** n);
}

const calc_probability_inverse = (k, n, s) => {
  let p = 0;
  for (let i = k; i <= n ; i++) {
    p += calc_probability(i, n, s);
  }
  return 1 - p;
}

// --------------

const sided = 6;
const dices = 5;
const rolls = 5;

const combinations = generate_all_combinations(sided, dices, rolls);

const occurrences = generate_occurrences(combinations);
console.log(occurrences);

const probabilities = generate_probabilities(occurrences);
console.log(probabilities);

const nodes = [];

const root = generate_node(0, dices, sided, 0);
tree(root);
// print_nodes_by(nodes, get_node_path);
// print_nodes_by(nodes, get_node_probability);

const tree_probability = calc_tree_probability(nodes);
console.log(tree_probability, `(${tree_probability * 100} %)`)
