package com.example.mealerapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.mealerapp.Activity.ClientActivity;

import org.junit.Rule;
import org.junit.Test;

public class ClientRegistrationTest {


    @Rule
    public ActivityScenarioRule<ClientActivity> mActivityTestRule = new ActivityScenarioRule<ClientActivity>(ClientActivity.class);



    @Test
    public void testEmail1() {

        onView(withId(R.id.editTextFirstName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("address"),closeSoftKeyboard());

        onView(withId(R.id.editTextEmail)).perform(typeText("email@"), closeSoftKeyboard());
        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextEmail)).check(matches(hasErrorText("Invalid Email")));

    }

    @Test
    public void testEmail2(){
        onView(withId(R.id.editTextFirstName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("address"),closeSoftKeyboard());


        onView(withId(R.id.editTextEmail)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("email"), closeSoftKeyboard());
        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextEmail)).check(matches(hasErrorText("Invalid Email")));

    }

    @Test
    public void testEmail3(){
        onView(withId(R.id.editTextFirstName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("address"),closeSoftKeyboard());


        onView(withId(R.id.editTextEmail)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("email.com"), closeSoftKeyboard());
        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextEmail)).check(matches(hasErrorText("Invalid Email")));
    }

    @Test
    public void testName1() {

        onView(withId(R.id.editTextEmail)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("address"),closeSoftKeyboard());


        onView(withId(R.id.editTextFirstName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextLastName)).check(matches(hasErrorText("Name is Required")));

    }

    @Test
    public void testName2(){
        onView(withId(R.id.editTextEmail)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("address"),closeSoftKeyboard());

        onView(withId(R.id.editTextFirstName)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextFirstName)).check(matches(hasErrorText("Name is Required")));
    }

    @Test
    public void testPassword1() {

        onView(withId(R.id.editTextFirstName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());



        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextConfirmPassword)).check(matches(hasErrorText("Please Confirm Password")));




    }

    @Test
    public void testPassword2(){
        onView(withId(R.id.editTextFirstName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());


        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("1234567"), closeSoftKeyboard());
        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextConfirmPassword)).check(matches(hasErrorText("Passwords do not match")));

    }

    @Test
    public void testPassword3(){
        onView(withId(R.id.editTextFirstName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.editTextPassword)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextPassword)).check(matches(hasErrorText("Password must be at least 6 characters")));
    }

    @Test
    public void testPassword4(){
        onView(withId(R.id.editTextFirstName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("address"),closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.editTextPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextPassword)).check(matches(hasErrorText("Password is Required")));
    }

    @Test
    public void testAddress() {
        onView(withId(R.id.editTextFirstName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("emailTest" + Math.random() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("123456"), closeSoftKeyboard());


        onView(withId(R.id.addPayment)).perform(click());
        onView(withId(R.id.editTextAddress)).check(matches(hasErrorText("Address is Required")));

    }



}
