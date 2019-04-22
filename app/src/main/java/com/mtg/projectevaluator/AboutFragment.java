package com.mtg.projectevaluator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Element versionElement = new Element();
            versionElement.setTitle("Version 1.0");
            Element adsElement = new Element();
            adsElement.setTitle("Advertise with Us");
            String description = "This is a app created as a project for Android Development course(2019)" +
                    "This app helps teachers for systematic evaluation of projects.\n" +
                    "Development Team:\n" +
                    "Mentor: Srinivas K S\n" +
                    "Manthan B Y\n" +
                    "Shankarnarayana Hebbar\n" +
                    "Amruthanshu Bhat\n" +
                    "Manoj Dandoti\n";
            View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                    .setDescription(description)
                .setImage(R.drawable.about_icon)
                .addItem(versionElement)
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("manthan.gowda@gmail.com")
                .addWebsite("http://medyo.github.io/")
                .addFacebook("the.medy")
                .addTwitter("medyo80")
                .addYoutube("UC5MglbZXyogaaeXcT1Ad8SQ")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addGitHub("medyo")
                .addInstagram("medyo80")
                .create();
            return aboutPage;
    }
}
