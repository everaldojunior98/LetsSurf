# Let's Surf

![Let's Surf](https://2017.spaceappschallenge.org/images/team-photosaLHcJpojT2pExxyINlKxSWYVtF43010width-800.jpg)

The App has 3 screens, simple and functional design. In the first screen we have a button to open the map, this one is in the second screen, its function is to choose locations for analysis, finally in the third screen we have the information on climatic and marine analyzes, these, referent to the selected location in the last screen.

The application even has a session of precautions on UV rays and waves for a perfect enjoyment of leisure. 

Uses an API to collect location data, and from a location you get access to wind speed, wind direction, humidity, uv index, significant wave height, water temperature, visibility.
And according to the UV received it returns different precautions, aiming at human health, for surfers they can use the app to choose the location with the biggest waves. 


## Activities
**SearchActivity:** Is the simplest screen of the project where the project logo is displayed and a button to proceed to the MapActivity.

**MapActivity:** Responsible for generating the map and picking up the location based on the user's click through the latitude and longitude provided by google maps.

**ResultActivity:** Through the API it takes all the information of the place selected by the user and presents to him, and from the UV index is generated possible precautions to prevent health problems.


## Functions
**JsoupAsyncTask:**
Are the functions for downloading the information received by the API, using the Jsoup library.

**setCodIcon:**
According to the climate API returns a code and from that code is made the selection of the icon to represent the condition of the site.

**setInfoAddress:**
From the downloaded XML it picks up and extracts the latitude and longitude, with it it directs the city and the state in which the point is located.

**returnValue:**
It extracts from a generic XML any value, only informing the key what value it is.

**setInfoMarine, setInfoWeather:**
With XML they extract the weather and sea data and put it in the listView, where this list shows other climatic conditions.
