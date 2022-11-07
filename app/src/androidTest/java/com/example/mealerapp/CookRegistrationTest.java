package com.example.mealerapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


public class CookRegistrationTest {
    @Rule
    public ActivityScenarioRule<CookActivity> mActivityTestRule = new ActivityScenarioRule<CookActivity>(CookActivity.class);

    @Test
    public void testEmail1() {

        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());

        onView(withId(R.id.editTextEmailCook)).perform(typeText("email@"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextEmailCook)).check(matches(hasErrorText("Invalid Email")));

    }

    @Test
    public void testEmail2(){
        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());



        onView(withId(R.id.editTextEmailCook)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextEmailCook)).perform(typeText("email"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextEmailCook)).check(matches(hasErrorText("Invalid Email")));

    }

    @Test
    public void testEmail3(){
        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());



        onView(withId(R.id.editTextEmailCook)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextEmailCook)).perform(typeText("email.com"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextEmailCook)).check(matches(hasErrorText("Invalid Email")));
    }

    @Test
    public void testName1() {

        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());


        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextLastNameCook)).check(matches(hasErrorText("Name is Required")));

    }

    @Test
    public void testName2(){
        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());


        onView(withId(R.id.editTextFirstNameCook)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextFirstNameCook)).check(matches(hasErrorText("Name is Required")));
    }

    @Test
    public void testPassword1() {

        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());
        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());





        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextConfirmPasswordCook)).check(matches(hasErrorText("Please Confirm Password")));




    }

    @Test
    public void testPassword2(){
        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());



        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("1234567"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextConfirmPasswordCook)).check(matches(hasErrorText("Passwords do not match")));

    }

    @Test
    public void testPassword3(){
        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());


        onView(withId(R.id.editTextPasswordCook)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextPasswordCook)).check(matches(hasErrorText("Password must be at least 6 characters")));
    }

    @Test
    public void testPassword4(){

        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());
        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());



        onView(withId(R.id.editTextPasswordCook)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextPasswordCook)).check(matches(hasErrorText("Password is Required")));
    }

    @Test
    public void testAddress() {
        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextDescription)).perform(typeText("description"), closeSoftKeyboard());



        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextAddressCook)).check(matches(hasErrorText("Address is Required")));

    }
    @Test
    public void testDescription() {
        onView(withId(R.id.editTextFirstNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastNameCook)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextEmailCook)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPasswordCook)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddressCook)).perform(typeText("address"),closeSoftKeyboard());

        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.editTextDescription)).check(matches(hasErrorText("Description is Required")));

    }


}
