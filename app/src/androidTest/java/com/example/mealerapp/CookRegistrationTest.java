package com.example.mealerapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class CookRegistrationTest {
    @Rule
    public ActivityScenarioRule<ClientActivity> mActivityTestRule = new ActivityScenarioRule<ClientActivity>(ClientActivity.class);



    @Test
    public void testEmail() {

        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());

        onView(withId(R.id.editTextEmailCook)).perform(typeText("email@"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Invalid Email")).check(matches(isDisplayed()));

        onView(withId(R.id.editTextEmailCook)).perform(typeText("email"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Invalid Email")).check(matches(isDisplayed()));

        onView(withId(R.id.editTextEmailCook)).perform(typeText("email.com"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Invalid Email")).check(matches(isDisplayed()));

    }

    @Test
    public void testName() {

        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)
        ).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());



        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Name is Required")).check(matches(isDisplayed()));

        onView(withId(R.id.editTextFirstNameCook)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Name is Required")).check(matches(isDisplayed()));

    }

    @Test
    public void testPassword() {

        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());

        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());



        onView(withId(R.id.editTextPasswordCook)
        ).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Please Confirm Password")).check(matches(isDisplayed()));

        onView(withId(R.id.editTextPasswordCook)
        ).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("1234567"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Passwords do not match")).check(matches(isDisplayed()));

        onView(withId(R.id.editTextPasswordCook)
        ).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Passwords must be at least 6 characters")).check(matches(isDisplayed()));

        onView(withId(R.id.editTextPasswordCook)
        ).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Password is required")).check(matches(isDisplayed()));


    }

    @Test
    public void testAddress() {
        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)
        ).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());



        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Address is Required")).check(matches(isDisplayed()));
    }




}
