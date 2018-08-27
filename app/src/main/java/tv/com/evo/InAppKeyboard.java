package tv.com.evo;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class InAppKeyboard extends LinearLayout implements View.OnClickListener, View.OnFocusChangeListener {

    // constructors
    public InAppKeyboard(Context context) {
        this(context, null, 0);
    }

    public InAppKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InAppKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private int flag = 0;
    private int flag_ABC = 0;
    public static int lastFocusedRow1, lastFocusedRow2, lastFocusedRow3;
    // in_app_keyboard keys (buttons)
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButton0;
    private Button mButtonq;
    private Button mButtonw;
    private Button mButtone;
    private Button mButtonr;
    private Button mButtont;
    private Button mButtony;
    private Button mButtonu;
    private Button mButtoni;
    private Button mButtono;
    private Button mButtonp;
    private Button mButtona;
    private Button mButtons;
    private Button mButtond;
    private Button mButtonf;
    private Button mButtong;
    private Button mButtonh;
    private Button mButtonj;
    private Button mButtonk;
    private Button mButtonl;
    private Button mButtonz;
    private Button mButtonx;
    private Button mButtonc;
    private Button mButtonv;
    private Button mButtonb;
    private Button mButtonn;
    private Button mButtonm;

    private Button mButtonQ;
    private Button mButtonW;
    private Button mButtonE;
    private Button mButtonR;
    private Button mButtonT;
    private Button mButtonY;
    private Button mButtonU;
    private Button mButtonI;
    private Button mButtonO;
    private Button mButtonP;
    private Button mButtonA;
    private Button mButtonS;
    private Button mButtonD;
    private Button mButtonF;
    private Button mButtonG;
    private Button mButtonH;
    private Button mButtonJ;
    private Button mButtonK;
    private Button mButtonL;
    private Button mButtonZ;
    private Button mButtonX;
    private Button mButtonC;
    private Button mButtonV;
    private Button mButtonB;
    private Button mButtonN;
    private Button mButtonM;

    private Button mButtonAt;
    private Button mButtonDot;
    private Button mButtonComma;
    private Button mButtonExclamation;
    //    private Button mButtonExclamation_open;
    private Button mButtonTilt;
    private Button mButtonHash;
    private Button mButtonDoller;
    //    private Button mButtonPercentage;
    private Button mButtonCap;
    private Button mButtonAnd;
    private Button mButtonStar;
    private Button mButtonOpenBracket;
    private Button mButtonCloseBracket;
    //    private Button mButtonOpenBrace;
//    private Button mButtonCloseBrace;
    private Button mButtonUnderscore;
    private Button mButtonHyphen;
    private Button mButtonPlus;
    //    private Button mButtonEqual;
//    private Button mButtonBackSlash;
    private Button mButtonFrontSlash;
    private Button mButtonLesserthan;
    private Button mButtonGreaterthan;
    private Button mButtonSemiColon;
    private Button mButtonColon;
//    private Button mButtonDoubleQoute;
//    private Button mButtonSingleQuote;

    private Button mButtonAtOpen;
    private Button mButtonAndOpenSmall;
    private Button mButtonUnderscoreOpenSmall;
    private Button mButtonHyphenOpenSmall;
    //    private Button mButtonQuestionOpenSmall;
    private Button mButtonCommaOpenSmall;

    private Button mButtonAndOpenCaps;
    private Button mButtonUnderscoreOpenCaps;
    private Button mButtonHyphenOpenCaps;
    //    private Button mButtonQuestionOpen;
    private Button mButtonDotCom;
    private Button mButtonDotOpen;
    private Button mButtonCommaOpenCaps;


    private Button mButtonDelete;
    private Button mButtonGmailOpen;
    private Button mButtonYahooOpen;
    private Button mButtonEvolutionOpen;
    //    private Button mButtonNext;
    private Button mButtonSym;
    private Button mButtonABC;
    private ImageView mButtonLeft;
    private ImageView mButtonRight;
    private LinearLayout smallLayout, capsLayout, symbolsLayout;

    // This will map the button resource id to the String value that we want to
    // input when that button is clicked.
    SparseArray<String> keyValues = new SparseArray<>();

    // Our communication link to the EditText
    InputConnection inputConnection;

    private void init(Context context, AttributeSet attrs) {

        // initialize buttons
        LayoutInflater.from(context).inflate(R.layout.in_app_keyboard, this, true);
        mButton1 = (Button) findViewById(R.id.button_1);
        mButton2 = (Button) findViewById(R.id.button_2);
        mButton3 = (Button) findViewById(R.id.button_3);
        mButton4 = (Button) findViewById(R.id.button_4);
        mButton5 = (Button) findViewById(R.id.button_5);
        mButton6 = (Button) findViewById(R.id.button_6);
        mButton7 = (Button) findViewById(R.id.button_7);
        mButton8 = (Button) findViewById(R.id.button_8);
        mButton9 = (Button) findViewById(R.id.button_9);
        mButton0 = (Button) findViewById(R.id.button_0);
        mButtonq = (Button) findViewById(R.id.button_q);
        mButtonw = (Button) findViewById(R.id.button_w);
        mButtone = (Button) findViewById(R.id.button_e);
        mButtonr = (Button) findViewById(R.id.button_r);
        mButtont = (Button) findViewById(R.id.button_t);
        mButtony = (Button) findViewById(R.id.button_y);
        mButtonu = (Button) findViewById(R.id.button_u);
        mButtoni = (Button) findViewById(R.id.button_i);
        mButtono = (Button) findViewById(R.id.button_o);
        mButtonp = (Button) findViewById(R.id.button_p);
        mButtona = (Button) findViewById(R.id.button_a);
        mButtons = (Button) findViewById(R.id.button_s);
        mButtond = (Button) findViewById(R.id.button_d);
        mButtonf = (Button) findViewById(R.id.button_f);
        mButtong = (Button) findViewById(R.id.button_g);
        mButtonh = (Button) findViewById(R.id.button_h);
        mButtonj = (Button) findViewById(R.id.button_j);
        mButtonk = (Button) findViewById(R.id.button_k);
        mButtonl = (Button) findViewById(R.id.button_l);
        mButtonz = (Button) findViewById(R.id.button_z);
        mButtonx = (Button) findViewById(R.id.button_x);
        mButtonc = (Button) findViewById(R.id.button_c);
        mButtonv = (Button) findViewById(R.id.button_v);
        mButtonb = (Button) findViewById(R.id.button_b);
        mButtonn = (Button) findViewById(R.id.button_n);
        mButtonm = (Button) findViewById(R.id.button_m);

        mButtonQ = (Button) findViewById(R.id.button_Q);
        mButtonW = (Button) findViewById(R.id.button_W);
        mButtonE = (Button) findViewById(R.id.button_E);
        mButtonR = (Button) findViewById(R.id.button_R);
        mButtonT = (Button) findViewById(R.id.button_T);
        mButtonY = (Button) findViewById(R.id.button_Y);
        mButtonU = (Button) findViewById(R.id.button_U);
        mButtonI = (Button) findViewById(R.id.button_I);
        mButtonO = (Button) findViewById(R.id.button_O);
        mButtonP = (Button) findViewById(R.id.button_P);
        mButtonA = (Button) findViewById(R.id.button_A);
        mButtonS = (Button) findViewById(R.id.button_S);
        mButtonD = (Button) findViewById(R.id.button_D);
        mButtonF = (Button) findViewById(R.id.button_F);
        mButtonG = (Button) findViewById(R.id.button_G);
        mButtonH = (Button) findViewById(R.id.button_H);
        mButtonJ = (Button) findViewById(R.id.button_J);
        mButtonK = (Button) findViewById(R.id.button_K);
        mButtonL = (Button) findViewById(R.id.button_L);
        mButtonZ = (Button) findViewById(R.id.button_Z);
        mButtonX = (Button) findViewById(R.id.button_X);
        mButtonC = (Button) findViewById(R.id.button_C);
        mButtonV = (Button) findViewById(R.id.button_V);
        mButtonB = (Button) findViewById(R.id.button_B);
        mButtonN = (Button) findViewById(R.id.button_N);
        mButtonM = (Button) findViewById(R.id.button_M);


        mButtonTilt = (Button) findViewById(R.id.button_tilt);
        mButtonAt = (Button) findViewById(R.id.button_at);
        mButtonHash = (Button) findViewById(R.id.button_hash);
        mButtonDoller = (Button) findViewById(R.id.button_doller);
//        mButtonPercentage = (Button) findViewById(R.id.button_percentage);
        mButtonCap = (Button) findViewById(R.id.button_cap);
        mButtonAnd = (Button) findViewById(R.id.button_and);
        mButtonStar = (Button) findViewById(R.id.button_star);
        mButtonOpenBracket = (Button) findViewById(R.id.button_open_bracket);
        mButtonCloseBracket = (Button) findViewById(R.id.button_close_bracket);
        mButtonHyphen = (Button) findViewById(R.id.button_hyphen);
        mButtonUnderscore = (Button) findViewById(R.id.button_underscore);
        mButtonPlus = (Button) findViewById(R.id.button_plus);
//        mButtonBackSlash = (Button) findViewById(R.id.button_back_slash);
        mButtonFrontSlash = (Button) findViewById(R.id.button_front_slash);
        mButtonGreaterthan = (Button) findViewById(R.id.button_greater_than);
        mButtonLesserthan = (Button) findViewById(R.id.button_lesser_than);
        mButtonSemiColon = (Button) findViewById(R.id.button_semicolon);
        mButtonColon = (Button) findViewById(R.id.button_colon);
        mButtonDot = (Button) findViewById(R.id.button_dot);
        mButtonComma = (Button) findViewById(R.id.button_comma);
        mButtonExclamation = (Button) findViewById(R.id.button_exclamation);
//        mButtonExclamation_open = (Button) findViewById(R.id.button_exclamation_open);


        mButtonDelete = (Button) findViewById(R.id.button_delete);
        mButtonGmailOpen = (Button) findViewById(R.id.button_gmail_open);
        mButtonYahooOpen = (Button) findViewById(R.id.button_yahoo_open);
        mButtonEvolutionOpen = (Button) findViewById(R.id.button_evolution_open);
//        mButtonNext = (Button) findViewById(R.id.button_next);
        mButtonSym = (Button) findViewById(R.id.button_SYM);
        mButtonABC = (Button) findViewById(R.id.button_ABC);
        mButtonLeft = (ImageView) findViewById(R.id.button_left);
        mButtonRight = (ImageView) findViewById(R.id.button_right);
        smallLayout = (LinearLayout) findViewById(R.id.small_layout);
        capsLayout = (LinearLayout) findViewById(R.id.caps_layout);
        symbolsLayout = (LinearLayout) findViewById(R.id.symbols_layout);


        mButtonAtOpen = (Button) findViewById(R.id.button_at_open);
        mButtonAndOpenSmall = (Button) findViewById(R.id.button_and_open_small);
        mButtonDotOpen = (Button) findViewById(R.id.button_dot_open);
        mButtonCommaOpenSmall = (Button) findViewById(R.id.button_comma_open_small);
        mButtonAtOpen = (Button) findViewById(R.id.button_at_open);
        mButtonAndOpenCaps = (Button) findViewById(R.id.button_and_open_caps);
        mButtonCommaOpenCaps = (Button) findViewById(R.id.button_comma_open_caps);
        mButtonUnderscoreOpenSmall = (Button) findViewById(R.id.button_underscore_open_small);
        mButtonUnderscoreOpenCaps = (Button) findViewById(R.id.button_underscore_open_caps);
        mButtonHyphenOpenCaps = (Button) findViewById(R.id.button_hyphen_open_caps);
        mButtonHyphenOpenSmall = (Button) findViewById(R.id.button_hyphen_open_small);
        mButtonDotCom = (Button) findViewById(R.id.button_com);
//        mButtonUnderscoreOpenCaps = (Button) findViewById(R.id.button_underscore_open_caps);
//        mButtonUnderscoreOpenSmall = (Button) findViewById(R.id.button_underscore_open_small);
//        mButtonHyphenOpenCaps = (Button) findViewById(R.id.button_hyphen_open_caps);
//        mButtonHyphenOpenSmall = (Button) findViewById(R.id.button_hyphen_open_small);
//        mButtonQuestionOpenCaps = (Button) findViewById(R.id.button_question_open_caps);
//        mButtonQuestionOpenSmall = (Button) findViewById(R.id.button_question_open_small);


        // set button click listeners
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButton0.setOnClickListener(this);

        mButtonq.setOnClickListener(this);
        mButtonw.setOnClickListener(this);
        mButtone.setOnClickListener(this);
        mButtonr.setOnClickListener(this);
        mButtont.setOnClickListener(this);
        mButtony.setOnClickListener(this);
        mButtonu.setOnClickListener(this);
        mButtoni.setOnClickListener(this);
        mButtono.setOnClickListener(this);
        mButtonp.setOnClickListener(this);
        mButtona.setOnClickListener(this);
        mButtons.setOnClickListener(this);
        mButtond.setOnClickListener(this);
        mButtonf.setOnClickListener(this);
        mButtong.setOnClickListener(this);
        mButtonh.setOnClickListener(this);
        mButtonj.setOnClickListener(this);
        mButtonk.setOnClickListener(this);
        mButtonl.setOnClickListener(this);
        mButtonz.setOnClickListener(this);
        mButtonx.setOnClickListener(this);
        mButtonc.setOnClickListener(this);
        mButtonv.setOnClickListener(this);
        mButtonb.setOnClickListener(this);
        mButtonn.setOnClickListener(this);
        mButtonm.setOnClickListener(this);

        mButtonQ.setOnClickListener(this);
        mButtonW.setOnClickListener(this);
        mButtonE.setOnClickListener(this);
        mButtonR.setOnClickListener(this);
        mButtonT.setOnClickListener(this);
        mButtonY.setOnClickListener(this);
        mButtonU.setOnClickListener(this);
        mButtonI.setOnClickListener(this);
        mButtonO.setOnClickListener(this);
        mButtonP.setOnClickListener(this);
        mButtonA.setOnClickListener(this);
        mButtonS.setOnClickListener(this);
        mButtonD.setOnClickListener(this);
        mButtonF.setOnClickListener(this);
        mButtonG.setOnClickListener(this);
        mButtonH.setOnClickListener(this);
        mButtonJ.setOnClickListener(this);
        mButtonK.setOnClickListener(this);
        mButtonL.setOnClickListener(this);
        mButtonZ.setOnClickListener(this);
        mButtonX.setOnClickListener(this);
        mButtonC.setOnClickListener(this);
        mButtonV.setOnClickListener(this);
        mButtonB.setOnClickListener(this);
        mButtonN.setOnClickListener(this);
        mButtonM.setOnClickListener(this);


        mButtonTilt.setOnClickListener(this);
        mButtonAt.setOnClickListener(this);
        mButtonHash.setOnClickListener(this);
        mButtonDoller.setOnClickListener(this);
        mButtonExclamation.setOnClickListener(this);
//        mButtonExclamation_open.setOnClickListener(this);
//        mButtonPercentage.setOnClickListener(this);
        mButtonCap.setOnClickListener(this);
        mButtonAnd.setOnClickListener(this);
        mButtonStar.setOnClickListener(this);
        mButtonOpenBracket.setOnClickListener(this);
        mButtonCloseBracket.setOnClickListener(this);
//        mButtonOpenBrace.setOnClickListener(this);
//        mButtonCloseBrace.setOnClickListener(this);
        mButtonHyphen.setOnClickListener(this);
        mButtonHyphenOpenSmall.setOnClickListener(this);
        mButtonHyphenOpenCaps.setOnClickListener(this);
        mButtonUnderscore.setOnClickListener(this);
        mButtonUnderscoreOpenCaps.setOnClickListener(this);
        mButtonUnderscoreOpenSmall.setOnClickListener(this);
        mButtonPlus.setOnClickListener(this);
//        mButtonBackSlash.setOnClickListener(this);
        mButtonFrontSlash.setOnClickListener(this);
        mButtonSemiColon.setOnClickListener(this);
        mButtonColon.setOnClickListener(this);
        mButtonGreaterthan.setOnClickListener(this);
        mButtonLesserthan.setOnClickListener(this);
//        mButtonEqual.setOnClickListener(this);
        mButtonDot.setOnClickListener(this);
        mButtonComma.setOnClickListener(this);
//        mButtonDoubleQoute.setOnClickListener(this);
//        mButtonSingleQuote.setOnClickListener(this);

        mButtonAt.setOnClickListener(this);
        mButtonAndOpenSmall.setOnClickListener(this);
//        mButtonUnderscoreOpenSmall.setOnClickListener(this);
//        mButtonHyphenOpenSmall.setOnClickListener(this);
        mButtonDotOpen.setOnClickListener(this);
        mButtonCommaOpenSmall.setOnClickListener(this);
        mButtonAtOpen.setOnClickListener(this);
        mButtonAndOpenCaps.setOnClickListener(this);
//        mButtonUnderscoreOpen.setOnClickListener(this);
//        mButtonHyphenOpen.setOnClickListener(this);
//        mButtonQuestionOpen.setOnClickListener(this);
        mButtonDotCom.setOnClickListener(this);
//        mButtonUnderscoreOpenSmall.setOnClickListener(this);
//        mButtonHyphenOpenSmall.setOnClickListener(this);
//        mButtonQuestionOpenSmall.setOnClickListener(this);
        mButtonDot.setOnClickListener(this);
        mButtonCommaOpenCaps.setOnClickListener(this);

        mButtonDelete.setOnClickListener(this);
        mButtonGmailOpen.setOnClickListener(this);
        mButtonYahooOpen.setOnClickListener(this);
        mButtonEvolutionOpen.setOnClickListener(this);
        mButtonSym.setOnClickListener(this);
        mButtonABC.setOnClickListener(this);
        mButtonLeft.setOnClickListener(this);
        mButtonRight.setOnClickListener(this);


        mButton1.setOnFocusChangeListener(this);
        mButton2.setOnFocusChangeListener(this);
        mButton3.setOnFocusChangeListener(this);
        mButton4.setOnFocusChangeListener(this);
        mButton5.setOnFocusChangeListener(this);
        mButton6.setOnFocusChangeListener(this);
        mButton7.setOnFocusChangeListener(this);
        mButton8.setOnFocusChangeListener(this);
        mButton9.setOnFocusChangeListener(this);
        mButton0.setOnFocusChangeListener(this);

        mButtonq.setOnFocusChangeListener(this);
        mButtonw.setOnFocusChangeListener(this);
        mButtone.setOnFocusChangeListener(this);
        mButtonr.setOnFocusChangeListener(this);
        mButtont.setOnFocusChangeListener(this);
        mButtony.setOnFocusChangeListener(this);
        mButtonu.setOnFocusChangeListener(this);
        mButtoni.setOnFocusChangeListener(this);
        mButtono.setOnFocusChangeListener(this);
        mButtonp.setOnFocusChangeListener(this);
        mButtona.setOnFocusChangeListener(this);
        mButtons.setOnFocusChangeListener(this);
        mButtond.setOnFocusChangeListener(this);
        mButtonf.setOnFocusChangeListener(this);
        mButtong.setOnFocusChangeListener(this);
        mButtonh.setOnFocusChangeListener(this);
        mButtonj.setOnFocusChangeListener(this);
        mButtonk.setOnFocusChangeListener(this);
        mButtonl.setOnFocusChangeListener(this);
        mButtonz.setOnFocusChangeListener(this);
        mButtonx.setOnFocusChangeListener(this);
        mButtonc.setOnFocusChangeListener(this);
        mButtonv.setOnFocusChangeListener(this);
        mButtonb.setOnFocusChangeListener(this);
        mButtonn.setOnFocusChangeListener(this);
        mButtonm.setOnFocusChangeListener(this);

        mButtonQ.setOnFocusChangeListener(this);
        mButtonW.setOnFocusChangeListener(this);
        mButtonE.setOnFocusChangeListener(this);
        mButtonR.setOnFocusChangeListener(this);
        mButtonT.setOnFocusChangeListener(this);
        mButtonY.setOnFocusChangeListener(this);
        mButtonU.setOnFocusChangeListener(this);
        mButtonI.setOnFocusChangeListener(this);
        mButtonO.setOnFocusChangeListener(this);
        mButtonP.setOnFocusChangeListener(this);
        mButtonA.setOnFocusChangeListener(this);
        mButtonS.setOnFocusChangeListener(this);
        mButtonD.setOnFocusChangeListener(this);
        mButtonF.setOnFocusChangeListener(this);
        mButtonG.setOnFocusChangeListener(this);
        mButtonH.setOnFocusChangeListener(this);
        mButtonJ.setOnFocusChangeListener(this);
        mButtonK.setOnFocusChangeListener(this);
        mButtonL.setOnFocusChangeListener(this);
        mButtonZ.setOnFocusChangeListener(this);
        mButtonX.setOnFocusChangeListener(this);
        mButtonC.setOnFocusChangeListener(this);
        mButtonV.setOnFocusChangeListener(this);
        mButtonB.setOnFocusChangeListener(this);
        mButtonN.setOnFocusChangeListener(this);
        mButtonM.setOnFocusChangeListener(this);


        mButtonTilt.setOnFocusChangeListener(this);
        mButtonAt.setOnFocusChangeListener(this);
        mButtonHash.setOnFocusChangeListener(this);
        mButtonDoller.setOnFocusChangeListener(this);
        mButtonExclamation.setOnFocusChangeListener(this);
//        mButtonExclamation_open.setOnFocusChangeListener(this);
//        mButtonPercentage.setOnFocusChangeListener(this);
        mButtonCap.setOnFocusChangeListener(this);
        mButtonAnd.setOnFocusChangeListener(this);
        mButtonStar.setOnFocusChangeListener(this);
        mButtonOpenBracket.setOnFocusChangeListener(this);
        mButtonCloseBracket.setOnFocusChangeListener(this);
//        mButtonOpenBrace.setOnFocusChangeListener(this);
//        mButtonCloseBrace.setOnFocusChangeListener(this);
        mButtonHyphen.setOnFocusChangeListener(this);
        mButtonHyphenOpenCaps.setOnFocusChangeListener(this);
        mButtonHyphenOpenSmall.setOnFocusChangeListener(this);
        mButtonUnderscore.setOnFocusChangeListener(this);
        mButtonUnderscoreOpenSmall.setOnFocusChangeListener(this);
        mButtonUnderscoreOpenCaps.setOnFocusChangeListener(this);
        mButtonPlus.setOnFocusChangeListener(this);
//        mButtonBackSlash.setOnFocusChangeListener(this);
        mButtonFrontSlash.setOnFocusChangeListener(this);
        mButtonSemiColon.setOnFocusChangeListener(this);
        mButtonColon.setOnFocusChangeListener(this);
        mButtonGreaterthan.setOnFocusChangeListener(this);
        mButtonLesserthan.setOnFocusChangeListener(this);
//        mButtonEqual.setOnFocusChangeListener(this);
        mButtonDot.setOnFocusChangeListener(this);
        mButtonComma.setOnFocusChangeListener(this);
//        mButtonDoubleQoute.setOnFocusChangeListener(this);
//        mButtonSingleQuote.setOnFocusChangeListener(this);

        mButtonAtOpen.setOnFocusChangeListener(this);
        mButtonAndOpenSmall.setOnFocusChangeListener(this);
//        mButtonUnderscoreOpenSmall.setOnFocusChangeListener(this);
//        mButtonHyphenOpenSmall.setOnFocusChangeListener(this);
        mButtonDotOpen.setOnFocusChangeListener(this);
        mButtonCommaOpenSmall.setOnFocusChangeListener(this);
        mButtonAndOpenCaps.setOnFocusChangeListener(this);
//        mButtonUnderscoreOpen.setOnFocusChangeListener(this);
//        mButtonHyphenOpen.setOnFocusChangeListener(this);
//        mButtonQuestionOpen.setOnFocusChangeListener(this);
        mButtonDotCom.setOnFocusChangeListener(this);
//        mButtonUnderscoreOpenSmall.setOnFocusChangeListener(this);
//        mButtonHyphenOpenSmall.setOnFocusChangeListener(this);
//        mButtonQuestionOpenSmall.setOnFocusChangeListener(this);
        mButtonCommaOpenCaps.setOnFocusChangeListener(this);

        mButtonDelete.setOnFocusChangeListener(this);
        mButtonGmailOpen.setOnFocusChangeListener(this);
        mButtonYahooOpen.setOnFocusChangeListener(this);
        mButtonEvolutionOpen.setOnFocusChangeListener(this);
//        mButtonNext.setOnFocusChangeListener(this);
        mButtonSym.setOnFocusChangeListener(this);
        mButtonABC.setOnFocusChangeListener(this);
        mButtonLeft.setOnFocusChangeListener(this);
        mButtonRight.setOnFocusChangeListener(this);


        // map buttons IDs to input strings
        keyValues.put(R.id.button_1, "1");
        keyValues.put(R.id.button_2, "2");
        keyValues.put(R.id.button_3, "3");
        keyValues.put(R.id.button_4, "4");
        keyValues.put(R.id.button_5, "5");
        keyValues.put(R.id.button_6, "6");
        keyValues.put(R.id.button_7, "7");
        keyValues.put(R.id.button_8, "8");
        keyValues.put(R.id.button_9, "9");
        keyValues.put(R.id.button_0, "0");
        keyValues.put(R.id.button_q, "q");
        keyValues.put(R.id.button_w, "w");
        keyValues.put(R.id.button_e, "e");
        keyValues.put(R.id.button_r, "r");
        keyValues.put(R.id.button_t, "t");
        keyValues.put(R.id.button_y, "y");
        keyValues.put(R.id.button_u, "u");
        keyValues.put(R.id.button_i, "i");
        keyValues.put(R.id.button_o, "o");
        keyValues.put(R.id.button_p, "p");
        keyValues.put(R.id.button_a, "a");
        keyValues.put(R.id.button_s, "s");
        keyValues.put(R.id.button_d, "d");
        keyValues.put(R.id.button_f, "f");
        keyValues.put(R.id.button_g, "g");
        keyValues.put(R.id.button_h, "h");
        keyValues.put(R.id.button_j, "j");
        keyValues.put(R.id.button_k, "k");
        keyValues.put(R.id.button_l, "l");
        keyValues.put(R.id.button_z, "z");
        keyValues.put(R.id.button_x, "x");
        keyValues.put(R.id.button_c, "c");
        keyValues.put(R.id.button_v, "v");
        keyValues.put(R.id.button_b, "b");
        keyValues.put(R.id.button_n, "n");
        keyValues.put(R.id.button_m, "m");

        keyValues.put(R.id.button_Q, "Q");
        keyValues.put(R.id.button_W, "W");
        keyValues.put(R.id.button_E, "E");
        keyValues.put(R.id.button_R, "R");
        keyValues.put(R.id.button_T, "T");
        keyValues.put(R.id.button_Y, "Y");
        keyValues.put(R.id.button_U, "U");
        keyValues.put(R.id.button_I, "I");
        keyValues.put(R.id.button_O, "O");
        keyValues.put(R.id.button_P, "P");
        keyValues.put(R.id.button_A, "A");
        keyValues.put(R.id.button_S, "S");
        keyValues.put(R.id.button_D, "D");
        keyValues.put(R.id.button_F, "F");
        keyValues.put(R.id.button_G, "G");
        keyValues.put(R.id.button_H, "H");
        keyValues.put(R.id.button_J, "J");
        keyValues.put(R.id.button_K, "K");
        keyValues.put(R.id.button_L, "L");
        keyValues.put(R.id.button_Z, "Z");
        keyValues.put(R.id.button_X, "X");
        keyValues.put(R.id.button_C, "C");
        keyValues.put(R.id.button_V, "V");
        keyValues.put(R.id.button_B, "B");
        keyValues.put(R.id.button_N, "N");
        keyValues.put(R.id.button_M, "M");


        keyValues.put(R.id.button_tilt, "~");
        keyValues.put(R.id.button_exclamation, "!");
//        keyValues.put(R.id.button_exclamation_open, "!");
        keyValues.put(R.id.button_at, "@");
        keyValues.put(R.id.button_at_open, "@");
        keyValues.put(R.id.button_hash, "#");
        keyValues.put(R.id.button_doller, "$");
//        keyValues.put(R.id.button_percentage, "%");
        keyValues.put(R.id.button_cap, "^");
        keyValues.put(R.id.button_and, "&");
        keyValues.put(R.id.button_and_open_caps, "&");
        keyValues.put(R.id.button_and_open_small, "&");
        keyValues.put(R.id.button_star, "*");
        keyValues.put(R.id.button_open_bracket, "(");
        keyValues.put(R.id.button_close_bracket, ")");
        keyValues.put(R.id.button_underscore, "_");
        keyValues.put(R.id.button_underscore_open_caps, "_");
        keyValues.put(R.id.button_underscore_open_small, "_");
        keyValues.put(R.id.button_hyphen, "-");
        keyValues.put(R.id.button_hyphen_open_caps, "-");
        keyValues.put(R.id.button_hyphen_open_small, "-");
        keyValues.put(R.id.button_plus, "+");
        keyValues.put(R.id.button_semicolon, ";");
        keyValues.put(R.id.button_colon, ":");
        keyValues.put(R.id.button_greater_than, "<");
        keyValues.put(R.id.button_lesser_than, ">");
        keyValues.put(R.id.button_front_slash, "/");
//        keyValues.put(R.id.button_back_slash, "\\");
        keyValues.put(R.id.button_dot, ".");
        keyValues.put(R.id.button_dot_open, ".");
        keyValues.put(R.id.button_comma, ",");
        keyValues.put(R.id.button_comma_open_small, ",");
        keyValues.put(R.id.button_comma_open_caps, ",");
        keyValues.put(R.id.button_com, ".com");


//        keyValues.put(R.id.button_underscore_open, "_");
//        keyValues.put(R.id.button_hyphen_open, "-");
//        keyValues.put(R.id.button_question_open, "?");

//        keyValues.put(R.id.button_underscore_open_small, "_");
//        keyValues.put(R.id.button_hyphen_open_small, "-");
//        keyValues.put(R.id.button_question_open_small, "?");

//        keyValues.put(R.id.button_underscore_open_caps, "_");
//        keyValues.put(R.id.button_hyphen_open_caps, "-");
//        keyValues.put(R.id.button_question_open_caps, "?");

        keyValues.put(R.id.button_gmail_open, "@gmail.com");
        keyValues.put(R.id.button_yahoo_open, "@yahoo.com");
        keyValues.put(R.id.button_evolution_open, "@evolutiondigital.com");


    }


    @Override
    public void onClick(View v) {

        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return;

        // Delete text or input key value
        // All communication goes through the InputConnection
        if (v.getId() == R.id.button_delete) {
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                // delete the selection
                inputConnection.commitText("", 1);
            }

        } else if (v.getId() == R.id.button_SYM) {
            if (flag == 0) {
                flag = 1; // 1 => Button ON
                mButtonSym.setText("abc");
                symbolsLayout.setVisibility(VISIBLE);
                smallLayout.setVisibility(GONE);
                capsLayout.setVisibility(GONE);
                lastFocusedRow1 = R.id.button_hyphen;
                findViewById(R.id.button_yahoo_open).setNextFocusUpId(R.id.button_underscore);
                findViewById(R.id.button_gmail_open).setNextFocusUpId(R.id.button_colon);
                findViewById(R.id.button_evolution_open).setNextFocusUpId(R.id.button_dot);
            } else {
                flag = 0; // 0 => Button OFF
                symbolsLayout.setVisibility(GONE);
                smallLayout.setVisibility(VISIBLE);
                capsLayout.setVisibility(GONE);
                mButtonSym.setText("Sym");
                lastFocusedRow1 = R.id.button_x;
                findViewById(R.id.button_yahoo_open).setNextFocusUpId(R.id.button_x);
                findViewById(R.id.button_gmail_open).setNextFocusUpId(R.id.button_b);
                findViewById(R.id.button_evolution_open).setNextFocusUpId(R.id.button_and_open_small);
            }
        } else if (v.getId() == R.id.button_ABC) {
            symbolsLayout.setVisibility(GONE);
            smallLayout.setVisibility(GONE);
            capsLayout.setVisibility(VISIBLE);
            lastFocusedRow1 = R.id.button_X;
            findViewById(R.id.button_yahoo_open).setNextFocusUpId(R.id.button_X);
            findViewById(R.id.button_gmail_open).setNextFocusUpId(R.id.button_B);
            findViewById(R.id.button_evolution_open).setNextFocusUpId(R.id.button_and_open_caps);


        } else {
            String value = keyValues.get(v.getId());
            inputConnection.commitText(value, 1);
        }
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.button_z) {
            if (lastFocusedRow2 == R.id.button_yahoo_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_x) {
            if (lastFocusedRow2 == R.id.button_yahoo_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_c) {
            if (lastFocusedRow2 == R.id.button_yahoo_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_v) {
            if (lastFocusedRow2 == R.id.button_gmail_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_b) {
            if (lastFocusedRow2 == R.id.button_gmail_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_n) {
            if (lastFocusedRow2 == R.id.button_gmail_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_m) {
            if (lastFocusedRow2 == R.id.button_gmail_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_comma_open_small) {
            if (lastFocusedRow2 == R.id.button_evolution_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_and_open_small) {
            if (lastFocusedRow2 == R.id.button_evolution_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_hyphen_open_small) {
            if (lastFocusedRow2 == R.id.button_evolution_open) {
                moveNextRowDown2(view);
            }
            lastFocusedRow1 = view.getId();
        }
        if (view.getId() == R.id.button_Z) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_X) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_C) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_V) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_B) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_N) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_M) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_comma_open_caps) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_and_open_caps) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_hyphen_open_caps) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_underscore) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_hyphen) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_plus) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_semicolon) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_colon) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_greater_than) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_lesser_than) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_front_slash) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_dot) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_comma) {
            lastFocusedRow1 = view.getId();
        } else if (view.getId() == R.id.button_yahoo_open) {
            if (lastFocusedRow1 == R.id.button_plus || lastFocusedRow1 == R.id.button_hyphen || lastFocusedRow1 == R.id.button_underscore || lastFocusedRow1 == R.id.button_z || lastFocusedRow1 == R.id.button_x || lastFocusedRow1 == R.id.button_c || lastFocusedRow1 == R.id.button_Z || lastFocusedRow1 == R.id.button_X || lastFocusedRow1 == R.id.button_C) {
                moveNextRowUp1(view);
            }
            if (lastFocusedRow3 == R.id.button_SYM || lastFocusedRow3 == R.id.button_ABC) {
                moveNextRowDown3(view);
            }
            lastFocusedRow2 = view.getId();
        } else if (view.getId() == R.id.button_gmail_open) {
            if (lastFocusedRow1 == R.id.button_greater_than || lastFocusedRow1 == R.id.button_lesser_than || lastFocusedRow1 == R.id.button_colon || lastFocusedRow1 == R.id.button_semicolon || lastFocusedRow1 == R.id.button_v || lastFocusedRow1 == R.id.button_b || lastFocusedRow1 == R.id.button_n || lastFocusedRow1 == R.id.button_m || lastFocusedRow1 == R.id.button_V || lastFocusedRow1 == R.id.button_B || lastFocusedRow1 == R.id.button_N || lastFocusedRow1 == R.id.button_M) {
                moveNextRowUp1(view);
            }
            if (lastFocusedRow3 == R.id.button_ABC || lastFocusedRow3 == R.id.button_at_open || lastFocusedRow3 == R.id.button_dot_open || lastFocusedRow3 == R.id.button_com) {
                moveNextRowDown3(view);
            }
            lastFocusedRow2 = view.getId();
        } else if (view.getId() == R.id.button_evolution_open) {
            if (lastFocusedRow1 == R.id.button_comma || lastFocusedRow1 == R.id.button_dot || lastFocusedRow1 == R.id.button_front_slash || lastFocusedRow1 == R.id.button_comma_open_small || lastFocusedRow1 == R.id.button_and_open_small || lastFocusedRow1 == R.id.button_hyphen_open_small || lastFocusedRow1 == R.id.button_comma_open_caps || lastFocusedRow1 == R.id.button_and_open_caps || lastFocusedRow1 == R.id.button_hyphen_open_caps) {
                moveNextRowUp1(view);
            }
            if (lastFocusedRow3 == R.id.button_left || lastFocusedRow3 == R.id.button_right || lastFocusedRow3 == R.id.button_delete) {
                moveNextRowDown3(view);
            }
            lastFocusedRow2 = view.getId();
        } else if (view.getId() == R.id.button_SYM) {

            lastFocusedRow3 = view.getId();
        } else if (view.getId() == R.id.button_ABC) {
            if (lastFocusedRow2 == R.id.button_gmail_open || lastFocusedRow2 == R.id.button_yahoo_open) {
                moveNextRowUp2(view);
            }
            lastFocusedRow3 = view.getId();
        } else if (view.getId() == R.id.button_at_open) {
            lastFocusedRow3 = view.getId();
        } else if (view.getId() == R.id.button_dot_open) {
            lastFocusedRow3 = view.getId();
        } else if (view.getId() == R.id.button_com) {
            lastFocusedRow3 = view.getId();
        } else if (view.getId() == R.id.button_left) {
            lastFocusedRow3 = view.getId();
        } else if (view.getId() == R.id.button_right) {
            lastFocusedRow3 = view.getId();
        } else if (view.getId() == R.id.button_delete) {
            lastFocusedRow3 = view.getId();
        }

    }

    private void moveNextRowUp1(View view) {
        view.setNextFocusUpId(lastFocusedRow1);
    }

    private void moveNextRowUp2(View view) {
        view.setNextFocusUpId(lastFocusedRow2);
    }

    private void moveNextRowDown2(View view) {
        view.setNextFocusDownId(lastFocusedRow2);
    }

    private void moveNextRowDown3(View view) {
        view.setNextFocusDownId(lastFocusedRow3);
    }
}
