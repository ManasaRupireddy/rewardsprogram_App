1. Eclipse with Java version 8 or above.
2. Download source code and import as import maven project in eclipse.
3. Right click project root and run as springboot app.
4. Hit the url : http://localhost:8080/customerrewards/get/1 to get Response from the API where 1 represents customerId for whom points summary is fetched.
This end point accepts a customer id and returns rewards points earned in each month and over all points earned.
Response : 
{"customerId":1,"totalPoints":21800,"monthlySummary":[{"points":5600,"yearMonth":"2022-05"},{"points":16200,"yearMonth":"2022-06"}]}

