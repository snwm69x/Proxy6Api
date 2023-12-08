package com.snwm.objects.response;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.annotations.SerializedName;
import com.snwm.enums.Country;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetCountryResponse extends ApiResponse {
    @SerializedName("list")
    List<String> list;

    public List<Country> getCountriesAsEnumList() {
        return list.stream()
                .map(Country::fromCode)
                .collect(Collectors.toList());
    }
}
