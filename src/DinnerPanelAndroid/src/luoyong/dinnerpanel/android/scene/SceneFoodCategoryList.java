package luoyong.dinnerpanel.android.scene;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import luoyong.dinnerpanel.android.DinnerPanelApplicationContext;
import luoyong.dinnerpanel.android.R;
import luoyong.dinnerpanel.android.model.FoodCategory;
import luoyong.dinnerpanel.android.rwsclient.FoodServiceClient;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SceneFoodCategoryList {

   public static int SCENE_INDEX = 2;

   private DinnerPanelApplicationContext dpAppContext = null;

   public SceneFoodCategoryList(DinnerPanelApplicationContext dpAppContext) {
      this.dpAppContext = dpAppContext;
   }

   public void show() {

      FoodServiceClient foodServiceClient = new FoodServiceClient();
      List<FoodCategory> foodCategoryList = null;
      try {
         foodCategoryList = foodServiceClient.getAllFoodCategories();
      }catch (Throwable t) {
         // Show error message.
         Toast toast = Toast.makeText(
                 this.dpAppContext.getActivityContext(),
                 t.getMessage(),
                 Toast.LENGTH_LONG);
         toast.show();
         // Log the exception.
         android.util.Log.e("DINNER PANEL ERROR", "", t);
         // Stop after the error message shown.
         return;
      }

      GridView gridView = (GridView)this.dpAppContext.findViewByIdFromMainView(
              R.id.scene_food_category_list_grid);
      gridView.setAdapter(
              new FoodCategoryViewAdapter(this.dpAppContext, foodCategoryList));

      // Set this scene as current scene.
      DinnerPanelApplicationContext.CURRENT_SCENE_INDEX = SCENE_INDEX;
      // Show current scene.
      this.dpAppContext.showCurrentScene();
   }

   private static class FoodCategoryViewAdapter extends BaseAdapter {

      static final private int IMAGE_DEFAULT_WIDTH = 100;
      static final private int IMAGE_DEFAULT_HEIGHT = 70;

      private DinnerPanelApplicationContext dpAppContext = null;
      private List<FoodCategory> foodCategoryList = null;

      private View[] views = null;

      public FoodCategoryViewAdapter(DinnerPanelApplicationContext dpAppContext,
              List<FoodCategory> foodCategoryList) {

         this.dpAppContext = dpAppContext;

         this.foodCategoryList = foodCategoryList;

         if (foodCategoryList == null) {
            return;
         }

         if (dpAppContext == null) {
            return;
         }

         int viewsCount = foodCategoryList.size();

         views = new View[viewsCount];

         for (int i=0; i<viewsCount; i++) {

            FoodCategory foodCategory = foodCategoryList.get(i);

            if (foodCategory == null) {
               continue;
            }

            // Load scaled bitmap.
            Bitmap bitmap = BitmapFactory.decodeResource(
                    dpAppContext.getResources(),
                    R.drawable.soup);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT, true);

            // Build image view.
            ImageView imageView = new ImageView(
                    dpAppContext.getActivityContext());
            imageView.setImageBitmap(bitmap);

            // Build text view.
            TextView textView = new TextView(dpAppContext.getActivityContext());
            textView.setText(foodCategory.getName());
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(dpAppContext.getResources().getColorStateList(
                    R.color.food_list_selector));

            // Build linear layout and add the image view and text view.
            LinearLayout linearLayout = new LinearLayout(
                    dpAppContext.getActivityContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            views[i] = linearLayout;
         }
      }

      public int getCount() {
         if (foodCategoryList == null) {
            return 0;
         }else {
            return foodCategoryList.size();
         }
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
}
