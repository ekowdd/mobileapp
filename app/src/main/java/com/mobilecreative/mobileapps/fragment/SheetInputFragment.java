package com.mobilecreative.mobileapps.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.mobilecreative.mobileapps.R;

/**
 * Created by eko on 11/10/17.
 */

public class SheetInputFragment extends BottomSheetFragment {
private  View view;
@Nullable
@Override
public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
	view = inflater.inflate(R.layout.sheet_fragment,container, false);
	return view;
}
}
