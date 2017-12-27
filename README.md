# SpinnerDatePicker

Steps to use the SpinnerDatePicker 

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.Basavaraj1991:SpinnerDatePicker:1.0.x'
	}
	
	Note: x is latest release version

Step 3. implement the IDatePicker interface in activity/fragment
        need to override methods.
        when ok button is clicked  below method will be called.
        @Override
        public void onOkClick(String s) {
        //do something here
        }
        
        when dialog dismiss below method will be called.
        @Override
        public void onDialogDismiss() {
          //do something here
        }
  

Step 4. 
       SpinnerDatePicker.getInstance(MainActivity.this)
                        .callback(MainActivity.this)
                        .setMaxDate(System.currentTimeMillis())   //optional
                        .setMinDate(System.currentTimeMillis())   //optional
                        .setTitle("Select Date of Birth")         //optional
                        .setButtonText("OK")                      //optional
                        .show();                                  //opens date picker


