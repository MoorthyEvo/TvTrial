package tv.com.evo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements WebServiceListener {

    private EditText emailEditText;
    private EditText passEditText;
    private TextView errorText, keypadLabel;
    Button forgotPwd;
    String deviceId;
    Button next, back, mButtonDelete;
    ImageView mButtonLeft, mButtonRight;
    String ipAddress;
    ProgressBar progressBar;
    DataProcessor dataProccessor;
    Dialog dialog, dialogP;
    TextView forgotPwdStatus;
    InAppKeyboard keyboard;
    InputConnection ic;
    LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callWidgetIds();
        dataProccessor = new DataProcessor(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ic = emailEditText.onCreateInputConnection(new EditorInfo());
                keyboard.setInputConnection(ic);
                widgetEvents();
                getDeviceDetails();
            }//public void run() {
        });

    }

    //get device details
    private void getDeviceDetails() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        int ip = wm.getConnectionInfo().getIpAddress();
        ipAddress = Formatter.formatIpAddress(ip);
        deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    private void callWidgetIds() {
        // Address the email and password field
        emailEditText = (EditText) findViewById(R.id.username);
        emailEditText.setText("chockalingam.s@evolutiondigital.com");
        passEditText = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        forgotPwd = (Button) findViewById(R.id.forgotPwd);
        errorText = (TextView) findViewById(R.id.error_text);
        keypadLabel = (TextView) findViewById(R.id.keypad_label);
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        keyboard = (InAppKeyboard) findViewById(R.id.keyboard);

        next = (Button) findViewById(R.id.button_next);
        back = (Button) findViewById(R.id.button_back);
        mButtonLeft = (ImageView) keyboard.findViewById(R.id.button_left);
        mButtonRight = (ImageView) keyboard.findViewById(R.id.button_right);
        mButtonDelete = (Button) keyboard.findViewById(R.id.button_delete);

        setEmailFocusable(true);
        setPasswordFocusable(false);

        emailEditText.setShowSoftInputOnFocus(false);
        passEditText.setShowSoftInputOnFocus(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            keyboard.setFocusedByDefault(true);
        }
    }


    private void widgetEvents() {

//        emailEditText.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//
//                if (isValidEmail(emailEditText.getText().toString())) {
//                    next.requestFocus();
//                }
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//// other stuffs
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//// other stuffs
//            }
//        });
        passEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (passEditText.getText().length() == 0) {
                    errorText.setText("Password cannot be empty");
                } else {
                    errorText.setText("");
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
// other stuffs
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
// other stuffs
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin(view);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                back.setFocusable(false);
                ic = emailEditText.onCreateInputConnection(new EditorInfo());
                keyboard.setInputConnection(ic);
                next.setText("Next");
                keypadLabel.setText("Enter your email");
                errorText.setText("");
                setEmailFocusable(true);
                setPasswordFocusable(false);
            }
        });
        next.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                view.setNextFocusUpId(InAppKeyboard.lastFocusedRow3);
            }
        });
        back.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                view.setNextFocusUpId(InAppKeyboard.lastFocusedRow3);
            }
        });
        mButtonDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                EditText focusedText = getEditText(emailEditText, passEditText);
                focusedText.getText().clear();
                return false;
            }
        });


        mButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText focusedText = getEditText(emailEditText, passEditText);
                int position = focusedText.getSelectionEnd();
                position--;
                if (position >= 0) {
                    Editable editObj = focusedText.getText();
                    Selection.setSelection(editObj, position);
                }
            }
        });
        mButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText focusedText = getEditText(emailEditText, passEditText);
                int position = focusedText.getSelectionEnd();
                position++;
                if (position <= focusedText.getText().length()) {
                    Editable editObj = focusedText.getText();
                    Selection.setSelection(editObj, position);
                }

            }
        });

        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setEmailFocusable(true);
                }
            }
        });
        passEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!emailEditText.hasFocus() && !hasFocus) {
                    setPasswordFocusable(true);
                }
            }
        });
//        back.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (emailEditText.hasFocus()&&hasFocus) {
//                    back.setFocusable(false);
//                }else {
//                    back.setFocusable(true);
//                }
//            }
//        });

        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForgotPwdWindow();
//                Toast.makeText(
//                        MainActivity.this, "Why so hurry!!! Yet to implement :)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private EditText getEditText(EditText email, EditText pass) {
        if (email.hasFocus()) {
            return email;
        } else if (pass.hasFocus()) {
            return pass;
        }
        return null;
    }

    private void showProgressPopup() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_progres);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.DIM_AMOUNT_CHANGED);
        dialog.show();
    }

    private void showForgotPwdWindow() {
        dialogP = new Dialog(this);
        dialogP.setCancelable(false);
        dialogP.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogP.setContentView(R.layout.forgot_pwd_page);
        final Window window = dialogP.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = dialogP.getWindow().getAttributes();
        lp.dimAmount = 0.9f;
        dialogP.getWindow().setAttributes(lp);
        dialogP.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialogP.show();

        final EditText emailEditTextP = (EditText) dialogP.findViewById(R.id.username);
        emailEditTextP.setText(emailEditText.getText().toString());
//        progressBar = (ProgressBar) dialog.findViewById(R.id.loading_spinner);
        forgotPwdStatus = (TextView) dialogP.findViewById(R.id.forgot_pwd_status);
        TextView keypadLabelP = (TextView) dialogP.findViewById(R.id.keypad_label);
        InAppKeyboard keyboardP = (InAppKeyboard) dialogP.findViewById(R.id.keyboard);

        Button nextP = (Button) dialogP.findViewById(R.id.button_next);
        Button mButtonCancelP = (Button) dialogP.findViewById(R.id.button_cancel);
        ImageView mButtonLeftP = (ImageView) keyboardP.findViewById(R.id.button_left);
        ImageView mButtonRightP = (ImageView) keyboardP.findViewById(R.id.button_right);
        Button mButtonDeleteP = (Button) keyboardP.findViewById(R.id.button_delete);


        setForgotEmailFocusable(emailEditTextP);
        emailEditTextP.setShowSoftInputOnFocus(false);


        InputConnection icP = emailEditTextP.onCreateInputConnection(new EditorInfo());
        keyboardP.setInputConnection(icP);
        forgotPwdWidgetEvents(emailEditTextP, nextP, mButtonLeftP, mButtonRightP, mButtonDeleteP, mButtonCancelP);

    }

    private void forgotPwdWidgetEvents(final EditText emailEditTextP, Button nextP, ImageView mButtonLeftP, ImageView mButtonRightP, Button mButtonDeleteP, Button mButtonCancelP) {
        emailEditTextP.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                emailEditTextP.setFocusable(true);
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        emailEditTextP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
//                    keyboard.setInputConnection(icEmail);
                    setForgotEmailFocusable(emailEditTextP);
                }
            }
        });

        nextP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidEmail(emailEditTextP.getText().toString())) {
                    forgotPwdStatus.setTextColor(getResources().getColor(R.color.error_text));
                    forgotPwdStatus.setText("Please enter valid Email ID");
                } else {
                    showProgressPopup();
                    JSONObject postDataParams = new JSONObject();
                    try {
                        postDataParams.put("cmd", "forgetPassword");
                        postDataParams.put("emailId", emailEditTextP.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    callApi(postDataParams, BaseUrl.baseUrl + "/idemand4u/evomaximizeV2.0/services");
                }
            }
        });
        mButtonCancelP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogP != null && dialogP.isShowing()) {
                    dialogP.setCancelable(true);
                    dialogP.dismiss();
                }
            }
        });

        mButtonDeleteP.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                emailEditTextP.getText().clear();
                return false;
            }
        });


        mButtonLeftP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = emailEditTextP.getSelectionEnd();
                position--;
                if (position >= 0) {
                    Editable editObj = emailEditTextP.getText();
                    Selection.setSelection(editObj, position);
                }
                if (!emailEditTextP.isFocusable()) {
                    emailEditTextP.setFocusable(true);
                    emailEditTextP.requestFocus();
                }
            }
        });
        mButtonRightP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = emailEditTextP.getSelectionEnd();
                position++;
                if (position <= emailEditTextP.getText().length()) {
                    Editable editObj = emailEditTextP.getText();
                    Selection.setSelection(editObj, position);
                }
            }
        });
    }

    private void setForgotEmailFocusable(EditText emailEditTextP) {
        emailEditTextP.setPressed(true);
        emailEditTextP.setSelection(emailEditTextP.getText().length());
        emailEditTextP.requestFocus();
    }

    private void callApi(JSONObject requestParams, String apiUrl) {
        Log.e("PostParams", requestParams.toString());
        WebServiceClient httpclient = new WebServiceClient(apiUrl, requestParams);
        httpclient.listener = this;
        httpclient.execute();
    }

    public void checkLogin(View arg0) {

        final String email = emailEditText.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            errorText.setText(R.string.valid_email_error);
        } else {
            ic = passEditText.onCreateInputConnection(new EditorInfo());
            keyboard.setInputConnection(ic);
            setPasswordFocusable(true);
            setEmailFocusable(false);
//            back.setFocusable(true);

//            back.setEnabled(true);
            next.setText("Sign In");
            keypadLabel.setText(R.string.enter_password);

        }

        final String pass = passEditText.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            errorText.setText(R.string.password_empty);
        }

        if (isValidEmail(email) && isValidPassword(pass)) {
            showProgressPopup();
            JSONObject postDataParams = new JSONObject();
            try {
                postDataParams.put("cmd", "accountAuthentication");
                postDataParams.put("emailId", emailEditText.getText().toString());
//              postDataParams.put("emailId", "chockalingam.s@evolutiondigital.com");
//              postDataParams.put("password", "saro@12345");
                postDataParams.put("password", passEditText.getText().toString());
                postDataParams.put("deviceId", deviceId);
                postDataParams.put("UUID", "");
                postDataParams.put("deviceType", "AndroidSTB");
                postDataParams.put("ipaddress", ipAddress);
                postDataParams.put("latitude", "");
                postDataParams.put("longitude", "");
                postDataParams.put("SSID", "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            callApi(postDataParams, BaseUrl.baseUrl + "/idemand4u/evomaximizeV2.0/services");
        }


    }

    public View getCurrentFocus() {
        View focusedView = (View) parentLayout.getFocusedChild();
        return focusedView;
    }

    private void setEmailFocusable(boolean focusable) {
        if (focusable) {
//            back.setEnabled(false);
            emailEditText.setFocusable(true);
            emailEditText.setPressed(true);
            emailEditText.setCursorVisible(true);
            emailEditText.setSelection(emailEditText.getText().length());
            emailEditText.requestFocus();
        } else {
            emailEditText.setFocusable(false);
            emailEditText.setPressed(false);
            emailEditText.setCursorVisible(false);
        }

    }

    private void setPasswordFocusable(boolean focusable) {
        if (focusable) {
            passEditText.setFocusable(true);
            passEditText.setCursorVisible(true);
            passEditText.setPressed(true);
            passEditText.setSelection(passEditText.getText().length());
            passEditText.requestFocus();
        } else {
            passEditText.setFocusable(false);
            passEditText.setPressed(false);
            passEditText.setCursorVisible(false);
        }
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }

    @Override
    public void onLoginActionComplete(String response) {
        // TODO Auto-generated method stub

        /*      Read the response     */
        try {
            JSONObject jsonObj = new JSONObject(response);
            Log.e("REsponse", jsonObj.toString());
            if (jsonObj.get("resultCode").toString().equals("200")) {
                if (jsonObj.has("session")) {
                    JSONObject session = jsonObj.getJSONObject("session");
                    JSONObject token = jsonObj.getJSONObject("token");
                    Map<String, String> map = new HashMap<String, String>();
                    Iterator sessionIter = session.keys();
                    Iterator tokenIter = token.keys();
                    while (sessionIter.hasNext()) {
                        String key = (String) sessionIter.next();
                        String value = session.getString(key);
                        dataProccessor.setStr(key, value);
                        map.put(key, value);
                    }
                    while (tokenIter.hasNext()) {
                        String key = (String) tokenIter.next();
                        String value = token.getString(key);
                        dataProccessor.setStr(key, value);
                    }
                    Intent intent = new Intent(MainActivity.this,
                            Success.class);
                    startActivity(intent);
                } else {
                    forgotPwdStatus.setTextColor(getResources().getColor(R.color.success_text));
                    forgotPwdStatus.setText("Password reset instructions have been sent to your email address.");
                }
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            } else {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    errorText.setText("Your Email or Password is incorrect.Please try again or go to your provider to verify your account.");
                    if (forgotPwdStatus != null) {
                        forgotPwdStatus.setTextColor(getResources().getColor(R.color.error_text));
                        forgotPwdStatus.setText(jsonObj.get("resultMessage").toString());
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
