package com.jsy_jiaobao.main.studentrecord;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jsy.xuezhuli.utils.BaseUtils;
import com.jsy.xuezhuli.utils.Constant;
import com.jsy.xuezhuli.utils.ConstantUrl;
import com.jsy.xuezhuli.utils.DialogUtil;
import com.jsy.xuezhuli.utils.EventBusUtil;
import com.jsy.xuezhuli.utils.GsonUtil;
import com.jsy.xuezhuli.utils.HttpUtil;
import com.jsy.xuezhuli.utils.ToastUtil;
import com.jsy_jiaobao.main.R;
import com.jsy_jiaobao.po.sturecord.GenWords;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

public class GenWordsFragmentController implements ConstantUrl {
	private static GenWordsFragmentController instance;
	private Fragment mcontext;
	private Context mContext;

	public static synchronized  GenWordsFragmentController getInstance() {
		if (instance == null) {
			instance = new GenWordsFragmentController();
		}
		return instance;
	}

	public GenWordsFragmentController setContext(Fragment noticeFragment) {
		mcontext = noticeFragment;
		mContext = noticeFragment.getActivity();
		return this;
	}

	/**
	 * 根据档案ID获取分学校未读数
	 */
	public void PackGenW(String DATA) {
		RequestParams params = new RequestParams();// CMD=BaseInfo&DATA=Uid|Recid|MsgType|PageSize|CurPage
		params.addBodyParameter("CMD", "PackGenW");
		params.addBodyParameter("DATA", DATA);
		CallBack callback = new CallBack();
		callback.setUserTag(Constant.sturecord_home_PackGenW);
		HttpUtil.getInstance().send(HttpRequest.HttpMethod.POST,
				StuRecordDataGet, params, callback);
	}

	private class CallBack extends RequestCallBack<String> {

		@Override
		public void onFailure(HttpException arg0, String arg1) {
			if (mContext != null) {
				try {
					if (mcontext.isAdded() && !mcontext.isDetached()) {
						dealResponseInfo("0", this.getUserTag());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (BaseUtils.isNetworkAvailable(mContext)) {
					ToastUtil.showMessage(mContext, R.string.phone_no_web);
				}
			}
		}

		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			if (mcontext.isAdded() && !mcontext.isDetached()) {
				DialogUtil.getInstance().cannleDialog();
				dealResponseInfo(arg0.result, this.getUserTag());
			}
		}
	}

	private void dealResponseInfo(String result, Object userTag) {
		ArrayList<Object> post = new ArrayList<>();
		switch ((Integer) userTag) {
		case Constant.sturecord_home_PackGenW:
			post.add(Constant.sturecord_home_PackGenW);
			GenWords genWords = GsonUtil.GsonToObject(result, GenWords.class);
			post.add(genWords);
			break;
		default:
			break;
		}
		EventBusUtil.post(post);
	}
}