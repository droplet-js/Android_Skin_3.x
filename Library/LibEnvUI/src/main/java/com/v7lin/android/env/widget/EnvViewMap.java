package com.v7lin.android.env.widget;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import java.util.HashMap;
import java.util.Map;

public class EnvViewMap {
    // 各类型视图的默认参数
    private static final Map<String, EnvViewParams> ENV_VIEW_PARAMS_MAP = new HashMap<String, EnvViewParams>();
    // 特殊需要转换兼容的视图
    private static final Map<String, String> ENV_VIEW_TRANSER_MAP = new HashMap<String, String>();

    static {
        // 各类型视图的默认参数
        ENV_VIEW_PARAMS_MAP.put(AutoCompleteTextView.class.getName(), new EnvViewParams(com.android.internal.R.attr.autoCompleteTextViewStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(Button.class.getName(), new EnvViewParams(com.android.internal.R.attr.buttonStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(CheckBox.class.getName(), new EnvViewParams(com.android.internal.R.attr.checkboxStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(CheckedTextView.class.getName(), new EnvViewParams(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ? com.android.internal.R.attr.checkedTextViewStyle : 0, 0, false));
        ENV_VIEW_PARAMS_MAP.put(Chronometer.class.getName(), new EnvViewParams(0, 0, false));
        ENV_VIEW_PARAMS_MAP.put(DatePicker.class.getName(), new EnvViewParams(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? com.android.internal.R.attr.datePickerStyle : 0, 0, false));
        ENV_VIEW_PARAMS_MAP.put(EditText.class.getName(), new EnvViewParams(com.android.internal.R.attr.editTextStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(ExpandableListView.class.getName(), new EnvViewParams(com.android.internal.R.attr.expandableListViewStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(FrameLayout.class.getName(), new EnvViewParams(0, 0, false));
        ENV_VIEW_PARAMS_MAP.put(GridView.class.getName(), new EnvViewParams(com.android.internal.R.attr.gridViewStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(HorizontalScrollView.class.getName(), new EnvViewParams(com.android.internal.R.attr.horizontalScrollViewStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(ImageButton.class.getName(), new EnvViewParams(com.android.internal.R.attr.imageButtonStyle, 0, false));
//        ENV_VIEW_PARAMS_MAP.put(ImageSwitcher.class.getName(), new EnvViewParams(0, 0, false));// 可以忽略不计
        ENV_VIEW_PARAMS_MAP.put(ImageView.class.getName(), new EnvViewParams(0, 0, false));
//        ENV_VIEW_PARAMS_MAP.put(LinearLayout.class.getName(), new EnvViewParams(0, 0, false));// 可以忽略不计
        ENV_VIEW_PARAMS_MAP.put(ListView.class.getName(), new EnvViewParams(com.android.internal.R.attr.listViewStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(ProgressBar.class.getName(), new EnvViewParams(com.android.internal.R.attr.progressBarStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(RadioButton.class.getName(), new EnvViewParams(com.android.internal.R.attr.radioButtonStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(RatingBar.class.getName(), new EnvViewParams(com.android.internal.R.attr.ratingBarStyle, 0, false));
//        ENV_VIEW_PARAMS_MAP.put(RelativeLayout.class.getName(), new EnvViewParams(0, 0, false));
        ENV_VIEW_PARAMS_MAP.put(ScrollView.class.getName(), new EnvViewParams(com.android.internal.R.attr.scrollViewStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(SeekBar.class.getName(), new EnvViewParams(com.android.internal.R.attr.seekBarStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(Spinner.class.getName(), new EnvViewParams(com.android.internal.R.attr.spinnerStyle, 0, false));
//        ENV_VIEW_PARAMS_MAP.put(TextSwitcher.class.getName(), new EnvViewParams(0, 0, false));
        ENV_VIEW_PARAMS_MAP.put(TextView.class.getName(), new EnvViewParams(com.android.internal.R.attr.textViewStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(TimePicker.class.getName(), new EnvViewParams(com.android.internal.R.attr.timePickerStyle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(ToggleButton.class.getName(), new EnvViewParams(com.android.internal.R.attr.buttonStyleToggle, 0, false));
        ENV_VIEW_PARAMS_MAP.put(View.class.getName(), new EnvViewParams(0, 0, false));
//        ENV_VIEW_PARAMS_MAP.put(ViewAnimator.class.getName(), new EnvViewParams(0, 0, false));
//        ENV_VIEW_PARAMS_MAP.put(ViewFlipper.class.getName(), new EnvViewParams(0, 0, false));
        ENV_VIEW_PARAMS_MAP.put(ViewGroup.class.getName(), new EnvViewParams(0, 0, false));
//        ENV_VIEW_PARAMS_MAP.put(ViewSwitcher.class.getName(), new EnvViewParams(0, 0, false));

        // 自己定制的
        ENV_VIEW_PARAMS_MAP.put(CompatNavigationBar.class.getName(), new EnvViewParams(0, 0, true));
        ENV_VIEW_PARAMS_MAP.put(CompatStatusBar.class.getName(), new EnvViewParams(0, 0, true));


        // --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- //

        // SDK 内置支持的视图需要转换兼容的视图（使用 SimpleName 作 key）
        ENV_VIEW_TRANSER_MAP.put(AutoCompleteTextView.class.getSimpleName(), CompatAutoCompleteTextView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(Button.class.getSimpleName(), CompatButton.class.getName());
        ENV_VIEW_TRANSER_MAP.put(CheckBox.class.getSimpleName(), CompatCheckBox.class.getName());
        ENV_VIEW_TRANSER_MAP.put(CheckedTextView.class.getSimpleName(), CompatCheckedTextView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(Chronometer.class.getSimpleName(), CompatChronometer.class.getName());
        ENV_VIEW_TRANSER_MAP.put(DatePicker.class.getSimpleName(), CompatDatePicker.class.getName());
        ENV_VIEW_TRANSER_MAP.put(EditText.class.getSimpleName(), CompatEditText.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ExpandableListView.class.getSimpleName(), CompatExpandableListView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(FrameLayout.class.getSimpleName(), CompatFrameLayout.class.getName());
        ENV_VIEW_TRANSER_MAP.put(GridView.class.getSimpleName(), CompatGridView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(HorizontalScrollView.class.getSimpleName(), CompatHorizontalScrollView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ImageButton.class.getSimpleName(), CompatImageButton.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ImageSwitcher.class.getSimpleName(), CompatImageSwitcher.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ImageView.class.getSimpleName(), CompatImageView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(LinearLayout.class.getSimpleName(), CompatLinearLayout.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ListView.class.getSimpleName(), CompatListView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ProgressBar.class.getSimpleName(), CompatProgressBar.class.getName());
        ENV_VIEW_TRANSER_MAP.put(RadioButton.class.getSimpleName(), CompatRadioButton.class.getName());
        ENV_VIEW_TRANSER_MAP.put(RatingBar.class.getSimpleName(), CompatRatingBar.class.getName());
        ENV_VIEW_TRANSER_MAP.put(RelativeLayout.class.getSimpleName(), CompatRelativeLayout.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ScrollView.class.getSimpleName(), CompatScrollView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(SeekBar.class.getSimpleName(), CompatSeekBar.class.getName());
        ENV_VIEW_TRANSER_MAP.put(Spinner.class.getSimpleName(), CompatSpinner.class.getName());
        ENV_VIEW_TRANSER_MAP.put(TextSwitcher.class.getSimpleName(), CompatTextSwitcher.class.getName());
        ENV_VIEW_TRANSER_MAP.put(TextView.class.getSimpleName(), CompatTextView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(TimePicker.class.getSimpleName(), CompatTimePicker.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ToggleButton.class.getSimpleName(), CompatToggleButton.class.getName());
        ENV_VIEW_TRANSER_MAP.put(View.class.getSimpleName(), CompatView.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ViewAnimator.class.getSimpleName(), CompatViewAnimator.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ViewFlipper.class.getSimpleName(), CompatViewFlipper.class.getName());
        ENV_VIEW_TRANSER_MAP.put(ViewSwitcher.class.getSimpleName(), CompatViewSwitcher.class.getName());

        // SDK 内置支持的视图需要转换兼容的视图（使用 Name 作 key）
    }

    public <UI extends View> void assetParams(Class<UI> clazz, EnvViewParams params) {
        ENV_VIEW_PARAMS_MAP.put(clazz.getName(), params);
    }

    public void assetTranser(Class<? extends View> clazz, Class<? extends View> compatClazz) {
        ENV_VIEW_TRANSER_MAP.put(clazz.getSimpleName(), compatClazz.getName());
    }

    public void assetThirdTranser(Class<? extends View> clazz, Class<? extends View> compatClazz) {
        ENV_VIEW_TRANSER_MAP.put(clazz.getName(), compatClazz.getName());
    }

    public String transfer(String name) {
        String transferName = ENV_VIEW_TRANSER_MAP.get(name);
        if (TextUtils.isEmpty(transferName)) {
            transferName = name;
        }
        return transferName;
    }

    public EnvViewParams obtainViewParams(Class<?> clazz) {
        EnvViewParams params = ENV_VIEW_PARAMS_MAP.get(clazz.getName());
        if (params == null) {
            params = obtainViewParams(clazz.getSuperclass());
        }
        return params;
    }

    private static final class EnvViewMapHolder {
        private static final EnvViewMap INSTANCE = new EnvViewMap();
    }

    public static EnvViewMap getInstance() {
        return EnvViewMapHolder.INSTANCE;
    }
}
