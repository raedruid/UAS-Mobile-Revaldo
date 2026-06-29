package com.example.uas_mobile_revaldo.data.network;

import com.example.uas_mobile_revaldo.data.model.Endemik;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    // Endpoint: https://hendroprwk08.github.io/data_endemik/endemik.json
    @GET("endemik.json")
    Call<List<Endemik>> getEndemik();
}
