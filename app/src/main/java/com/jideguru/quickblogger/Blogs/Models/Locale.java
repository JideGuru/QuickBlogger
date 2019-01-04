package com.jideguru.quickblogger.Blogs.Models;

/**
* Created by jideguru on 14/09/2018.
*/
public class Locale
{
    public String country;
    public String variant;
    public String language;

    public Locale(String country, String variant, String language) {
        this.country = country;
        this.variant = variant;
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
