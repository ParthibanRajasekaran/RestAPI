package GooglePlaces;

public class payLoad {

    public static String createValidPlaceViaPOST(){
        String createPlace = "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -33.8669710,\n" +
                "    \"lng\": 151.1958750\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Gaurd Of Honour!\",\n" +
                "  \"phone_number\": \"(02) 9374 4000\",\n" +
                "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n" +
                "  \"types\": [\"restaurant\"],\n" +
                "  \"website\": \"http://www.google.com.au/\",\n" +
                "  \"language\": \"en-AU\"\n" +
                "}";
        return createPlace;
    }

    public static String deletePlace(String placeId){
        String deletePlace = "{\n" +
                "  \"place_id\": \""+placeId+"\"\n" +
                "}";
        return deletePlace;
    }

}
