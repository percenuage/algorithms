console.log("23280666028113")

function minimumTime(numOfParts, parts)
{
    console.log(numOfParts, parts)

    const pairs = [];
    const remaining = [...parts];

    while(remaining.length > 1) {
        if (remaining.length > 2) {
            remaining.sort((a, b) => a - b);
        }
        let sum = remaining.shift();
        sum += remaining.shift();
        //console.log('sum', sum)
        pairs.push(sum);
        remaining.push(sum);
        //console.log(pairs)
        //console.log(remaining)
    }

    return pairs.reduce((acc, val) => acc + val, 0);


}


// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME FUNCTIONALITY WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY FUNCTION NEEDED
// FUNCTION SIGNATURE BEGINS, THIS FUNCTION IS REQUIRED
function optimalUtilization(maxTravelDist, forwardRouteList, returnRouteList)
{
    console.log(maxTravelDist,forwardRouteList,returnRouteList)

    let routes = [];
    let last = 0;


    forwardRouteList.forEach(fRoute => {
        returnRouteList.forEach(rRoute => {
            console.log("last", last)
            const sum = fRoute[1] + rRoute[1];
            console.log("sum", sum)
            if (sum >= last && sum <= maxTravelDist) {
                if (last === maxTravelDist) {
                    routes.push([fRoute[0], rRoute[0]]);
                } else {
                    routes = [[fRoute[0], rRoute[0]]];
                }

                last = sum;
            }


            console.log("route", routes)
        })
    })

    return routes;

}
// FUNCTION SIGNATURE ENDS