package com.buildmlearn.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.buildmlearn.activities.TemplateActivity;
import com.example.buildmlearntoolkit.R;

public class HelpGenerator {
	
	public static void showHelp(Context context)
	{

   	 new MaterialDialog.Builder(context)
               .title("Welcome to Help")
               .content(R.string.help_text)
               .positiveText(R.string.agree)
               .positiveColorRes(R.color.primaryColor)
               .negativeColorRes(R.color.primaryColor)
               .titleGravity(GravityEnum.CENTER)
               .titleColorRes(R.color.primaryColor)
               .contentColorRes(android.R.color.white)
               .backgroundColorRes(R.color.material_blue_grey_800)
               .dividerColorRes(R.color.status_bar)
               .btnSelector(R.drawable.md_btn_selector_custom, DialogAction.POSITIVE)
               .positiveColor(Color.WHITE)
               .negativeColorAttr(android.R.attr.textColorSecondaryInverse)
               .theme(Theme.LIGHT)
               .callback(new MaterialDialog.ButtonCallback() {
                   @Override
                   public void onPositive(MaterialDialog dialog) {
                   	
                   	
                   	
                   }

                  
               })
               .show();
	}

}
