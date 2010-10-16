package luoyong.dinnerpanel.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class MainActivity extends Activity {

   private ViewSwitcher mainView = null;
   
   private Dialog confirmDialog = null;

   private Gallery gallery = null;

   private Activity activityContext = null;

   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);

      this.activityContext = this;

      this.setContentView(R.layout.main);
      
      this.mainView = (ViewSwitcher)this.findViewById(R.id.main_view);

      // Setup food category grid view.
      GridView gridView
              = (GridView)this.findViewById(R.id.food_category_view_grid);
      gridView.setAdapter(new FoodCategoryViewAdapter(this));


      // Prepair the layout inflater.
      LayoutInflater layoutInflater
              = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
      // Get view of the confirm dialog.
      View confirmDialogView = layoutInflater.inflate(
              R.layout.confirm_dialog_layout,
              (ViewGroup)this.findViewById(R.id.confirm_dialog_main_view));

      // Prepair the confirm dialog builder.
      AlertDialog.Builder confirmDialogBuilder = new AlertDialog.Builder(this);
      confirmDialogBuilder.setView(confirmDialogView);
      confirmDialogBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface arg0, int arg1) {
         }
      });
      confirmDialogBuilder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface arg0, int arg1) {
         }
      });
      // Build the confirm dialog.
      confirmDialog = confirmDialogBuilder.create();


      // Setup food list gallery.
      gallery = (Gallery)this.findViewById(R.id.food_list_view_gallery);
      gallery.setAdapter(new FoodListViewAdapter(this));

      Button buttonFoodListViewAdd
              = (Button)this.findViewById(R.id.food_list_view_button_add);
      buttonFoodListViewAdd.setOnClickListener(new OnClickListener() {
         public void onClick(View arg0) {
            confirmDialog.show();
         }
      });

      Button buttonFoodListViewBack
              = (Button)this.findViewById(R.id.food_list_view_button_back);
      buttonFoodListViewBack.setOnClickListener(new OnClickListener() {
         public void onClick(View arg0) {
            mainView.setDisplayedChild(0);
         }
      });

      
      // 
      gridView.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
            
            if (position == 2) {
               Toast toast = Toast.makeText(activityContext, "请稍候，正在获取数据", Toast.LENGTH_LONG);
               toast.show();
               activityContext.showDialog(0);
               gallery.setAdapter(new FoodListViewAdapter(activityContext));
               mainView.setDisplayedChild(1);
               activityContext.dismissDialog(0);
            }else {
               Toast toast = Toast.makeText(activityContext, "很抱歉，此分类下没有餐品", Toast.LENGTH_SHORT);
               toast.show();
            }
         }
      });
   }

   private void getData() {
      this.showDialog(0);
      // Get the data.
   }

   @Override
   protected Dialog onCreateDialog(int id) {
      ProgressDialog dialog = new ProgressDialog(this);
      dialog.setTitle("请稍候");
      dialog.setMessage("请稍候，正在获取数据");
      return dialog;
      // Dialog dialog = ProgressDialog.show(activityContext, "请稍候", "请稍候，正在获取数据");
   }

   public static class FoodCategoryViewAdapter extends BaseAdapter {

      static final private int IMAGE_DEFAULT_WIDTH = 100;
      static final private int IMAGE_DEFAULT_HEIGHT = 70;

      private LinearLayout views[] = new LinearLayout[10];

      private Activity activity = null;

      public FoodCategoryViewAdapter(Activity activity) {
         this.activity = activity;

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.hot_dish);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("热菜");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[0] = linearLayout;
         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.cold_dish);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("冷菜");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[1] = linearLayout;
         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.staple_food);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("主食");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[2] = linearLayout;
         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.soup);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("汤");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[3] = linearLayout;
         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.soup);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("测试分类");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[4] = linearLayout;
         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.soup);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("测试分类");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[5] = linearLayout;
         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.soup);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("测试分类");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[6] = linearLayout;
         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.soup);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("测试分类");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[7] = linearLayout;
         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.soup);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("测试分类");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[8] = linearLayout;
         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.soup);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("测试分类");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[9] = linearLayout;
         }
      }

      public int getCount() {
         return views.length;
      }

      public Object getItem(int arg0) {
         return arg0;
      }

      public long getItemId(int arg0) {
         return arg0;
      }

      public View getView(int arg0, View arg1, ViewGroup arg2) {

         if ((arg0 >= 0) && (arg0 <this.getCount())) {
            return views[arg0];
         }else {
            return null;
         }
      }
   }

   public static class FoodListViewAdapter extends BaseAdapter {

      private int imageWidth = 200;
      private int imageHeight = 200;

      private LinearLayout views[] = new LinearLayout[7];

      private Activity activity = null;

      public FoodListViewAdapter(Activity activity) {
         this.activity = activity;
//         imageWidth =
//                 this.activity.getWindowManager().getDefaultDisplay().getWidth()
//                  * 3 / 4;
//         imageHeight =
//                 this.activity.getWindowManager().getDefaultDisplay().getHeight()
//                  * 3 / 4;

         {

            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.pasta_mostaciolli);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, imageWidth, imageHeight, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("Mostaciolli");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[0] = linearLayout;

         }

         {

            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.pasta_farfalle);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, imageWidth, imageHeight, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("Farfalle");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[1] = linearLayout;

         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.pasta_macaroni);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, imageWidth, imageHeight, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("Macaroni");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[2] = linearLayout;

         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.pasta_conchiglie);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, imageWidth, imageHeight, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("Conchiglie");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[3] = linearLayout;

         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.pasta_rotini);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, imageWidth, imageHeight, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("Rotini");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[4] = linearLayout;

         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.pasta_spahetti);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, imageWidth, imageHeight, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("Spahetti");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[5] = linearLayout;

         }

         {
            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    activity.getResources(), R.drawable.pasta_ziti);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, imageWidth, imageHeight, true);

            // Build image view.
            ImageView imageView = new ImageView(activity);
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(activity);
            textView.setText("Ziti");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(activity.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[6] = linearLayout;
         }
      }

      public int getCount() {
         return views.length;
      }

      public Object getItem(int arg0) {
         return arg0;
      }

      public long getItemId(int arg0) {
         return arg0;
      }

      public View getView(int arg0, View arg1, ViewGroup arg2) {

         if ((arg0 >=0) && (arg0 < views.length)) {
            return views[arg0];
         }else {
            return null;
         }
      }
   }

   public static class EmptyFoodListViewAdapter extends BaseAdapter {

      private Activity activity = null;

      public EmptyFoodListViewAdapter(Activity activity) {
         this.activity = activity;
      }

      public int getCount() {
         return 0;
      }

      public Object getItem(int arg0) {
         return arg0;
      }

      public long getItemId(int arg0) {
         return arg0;
      }

      public View getView(int arg0, View arg1, ViewGroup arg2) {
         return null;
      }
   }
}
