# exchangerateconversion
Application Work flow:
• This application will pull the data from external API(Rate API) and save the data into H2 DB.
Application Flow:
This application has five end points
• First Endpoint: When hit end point will call Rate API and get the one year data for GBP/USD/HKD currencies and save into H2 DB.
• Second Endpoint: When hit endpoint we need to pass input date as part of endpoint url, will get data for input date from rate API and will save into H2 DB.
• Third Endpoint: When hit endpoint will call Rate API and get one year data for all currencies and save into H2 DB.
• Fourth Endpoint:When hit endpoint we need to pass input date as part of endpoint url, Will get data for input date from H2 DB and display to Browser console or Postman.
• Fifth Endpoint:  When hit endpoint we need to pass two input dates as part of endpoint url, will get data in between two dates and today date data.
