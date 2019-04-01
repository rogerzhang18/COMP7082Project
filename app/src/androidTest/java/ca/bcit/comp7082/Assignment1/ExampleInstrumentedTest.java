package ca.bcit.comp7082.Assignment1;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;

import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.contrib.*;
//import static android.support.test.espresso.intent.Intents.intended;
//import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
//import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
//import static android.support.test.espresso.intent.matcher.IntentMatchers.hasCategories;
//import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
//import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
//import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
//import static android.support.test.espresso.intent.matcher.UriMatchers.hasHost;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(AndroidJUnit4.class)
//public class ExampleInstrumentedTest {
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("ca.bcit.comp7082.Assignment1", appContext.getPackageName());
//    }
//}
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTextChangesWork() {
        // Type text and then press the button.
        onView(withId(R.id.caption))
                .perform(typeText("Roger test"), closeSoftKeyboard());
        // Check that the text was changed.
        onView(withId(R.id.caption)).check(matches(withText("Roger test")));
        //onView(withContentDescription("Navigate up")).perform(click());
        onView(withId(R.id.rotate)).perform(click());
        onView(withId(R.id.rotate)).perform(click());
        onView(withId(R.id.rotate)).perform(click());
        onView(withId(R.id.rotate)).perform(click());

        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.datepicker)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000, 06, 21));
        onView(withText("OK")).perform(click());
//        onView(withId(R.id.locationText))
//                .perform(typeText("Vancouver"), closeSoftKeyboard());
        onView(withId(R.id.button_search)).perform(click());

        //Espresso.pressBack();
//        onView(withId(R.id.caption))
//                .perform(typeText(""), closeSoftKeyboard());        //mDevice.pressBack();
//
//        onView(withId(R.id.snap)).perform(click());

        //Espresso.pressBack();
//        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        mDevice.pressBack();

//        for (int i=0; i<=5; i++)
//        {
//            onView(withId(R.id.button_search)).perform(click());
//        }
    }

    // @Rule
    //public IntentsTestRule<MainActivity> intentsRule = new IntentsTestRule<>(MainActivity.class);

//    @Test
//    public void validateCameraScenario() {
//        // Create a bitmap we can use for our simulated camera image
//        Bitmap icon = BitmapFactory.decodeResource(
//                InstrumentationRegistry.getTargetContext().getResources(),
//                R.mipmap.ic_launcher);
//
//        // Build a result to return from the Camera app
//        Intent resultData = new Intent();
//        resultData.putExtra("data", icon);
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
//
//        // Stub out the Camera. When an intent is sent to the Camera, this tells Espresso to respond
//        // with the ActivityResult we just created
//        intending(toPackage("ca.bcit.comp7082.Assignment1")).respondWith(result);
//
//        // Now that we have the stub in place, click on the button in our app that launches into the Camera
//        onView(withId(R.id.snap)).perform(click());
//
//        // We can also validate that an intent resolving to the "camera" activity has been sent out by our app
//        intended(toPackage("ca.bcit.comp7082.Assignment1"));
//    }
}