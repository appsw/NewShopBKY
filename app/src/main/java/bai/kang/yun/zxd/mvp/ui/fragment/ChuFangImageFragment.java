package bai.kang.yun.zxd.mvp.ui.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.base.BaseFragment;

import java.io.File;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.CommonUtil;
import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class ChuFangImageFragment extends BaseFragment implements View.OnClickListener{


    @BindView(R.id.im_jia)
    ImageView im_im;
    private Dialog dialog;
    private View inflate;
    private TextView chose;
    private TextView take;
    private final int TAKE_PICTURE=1;
    private final int CHOOSE_PICTURE=2;
    private final int CROP_SMALL_PICTURE=3;
    private Bitmap mBitmap;
    private Uri  tempUri;
    public static ChuFangImageFragment newInstance() {
        ChuFangImageFragment fragment = new ChuFangImageFragment();
        return fragment;
    }
    @Override
    protected void ComponentInject() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_chufangimage, container, false);
    }

    @Override
    protected void initData() {

    }
    @OnClick(R.id.im_jia)
    void getImg(){
        dialog = new Dialog(getActivity(),R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choselink, null);
        //初始化控件
        take = (TextView) inflate.findViewById(R.id.qq);
        chose = (TextView) inflate.findViewById(R.id.call);
        take.setText("拍照");
        chose.setText("从相册选择");
        take.setOnClickListener(this);
        chose.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qq:
                take();
                break;
            case R.id.call:
                if (Build.VERSION.SDK_INT >= 23) {
                    if (getActivity().getApplication().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        this.requestPermissions(
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                333);

                    } else {
                        chose();
                    }
                }else {
                    chose();
                }
                break;
        }
        dialog.dismiss();

    }
    public void take(){
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = getActivity().getApplication().checkSelfPermission( Manifest.permission.CAMERA);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                this.requestPermissions(new String[]{Manifest.permission.CAMERA},222);
                return;
            }else{

                openCamra();//调用具体方法
            }
        } else {

            openCamra();//调用具体方法
        }

    }

    /**
     * 打开系统相机
     */

    private void openCamra(){
        File file = new File(
                Environment.getExternalStorageDirectory().getAbsolutePath()
                , "image");

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
////            tempUri = CommonUtil.getImageContentUri(this, file);//通过FileProvider创建一个content类型的Uri
//            tempUri = FileProvider.getUriForFile(this, "bai.kang.yun.zxd.provider", file);
//
//        } else {
//            tempUri = Uri.fromFile(file);
//        }
        tempUri = Uri.fromFile(file);
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            openCameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }

        // 将拍照所得的相片保存到SD卡根目录
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);

    }
    public void chose(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent openAlbumIntent = new Intent(
                Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
        startActivityForResult(intent, CHOOSE_PICTURE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    cut(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    cut(data.getData()); // 对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }
    public void cut(Uri uri){
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().getApplication().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        111);

            } else {
                cutImage(uri);
            }
        }else {
            cutImage(uri);
        }
    }
    /**
     * 裁剪图片方法实现
     */
    protected void cutImage(Uri uri) {
        if (uri == null) {
            Log.i("dyp", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }
    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
//            mBitmap = CommonUtil.toRoundBitmap(mBitmap, tempUri);//因项目需求，把图片转成圆形
            im_im.setImageBitmap(mBitmap);//头像设置为新的图片
            uploadPic(mBitmap);//上传图片到服务器
        }
    }
    private void uploadPic(Bitmap bitmap) {

        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        String imagePath = CommonUtil.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            File file = new File(imagePath);
//            在这里把file上传服务器
//            mPresenter.uploadHeadPic(file);
//            presenter.uploadHeadPic(((AppContext) getActivity().getApplication()).getBean().getCompanyCode(),file);//这里是P层把图片上传到服务器。
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == 222)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                take();
            } else
            {
                // Permission Denied
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == 333)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                chose();
            } else
            {
                // Permission Denied
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 111)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

            } else
            {
                // Permission Denied
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}