package com.example.appmusiconline.Service;

public class APIService {
    private static String base_url = "https://duydonvn.000webhostapp.com/server/";

    // dữ liệu sẽ đc trả về cho DataService ở class khác => nó là kiểu trả về ???
    public static DataService getService(){
        // create khoi tao nhung method http ben DataService de gui len
        // mà dataservice chỉ có method http get thôi
        // lúc này sẽ có 1 url đầy đủ luôn và retrofit đến url đó ... làm 1 cái gì đó
        // sau đó trả về kdl của interface này

        return APIRetrofitClient.getClient(base_url).create(DataService.class);

        // retrofit.create (DataService.class);
    }
}
