package com.example.PersonalizedLearningPlatform.common.enums;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Country {
  private String displayName;

  public Country(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static List<Country> getAllCountries() {
    List<Country> countries = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/main/resources/countries.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        countries.add(new Country(line.trim()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return countries;
  }
}