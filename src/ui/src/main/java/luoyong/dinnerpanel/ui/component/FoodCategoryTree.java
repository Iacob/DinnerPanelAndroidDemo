package luoyong.dinnerpanel.ui.component;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.dao.model.FoodCategory;
import luoyong.dinnerpanel.rwsclient.FoodServiceClient;
import luoyong.dinnerpanel.rwscommon.info.RWSException;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodCategoryTree extends JTree {

   private DefaultMutableTreeNode rootNode = null;
   private DefaultTreeModel model = null;

   private LinkedList<DefaultMutableTreeNode> nodeList = null;

   private FoodServiceClient foodServiceClient = null;

   public FoodCategoryTree() {
      super();
      rootNode = new DefaultMutableTreeNode("餐品分类");
      rootNode.setAllowsChildren(true);
      model = new DefaultTreeModel(rootNode);
      this.setEditable(true);
      this.setModel(model);
      
      DefaultTreeSelectionModel defaultSelectionModel
              = new DefaultTreeSelectionModel();
      defaultSelectionModel.setSelectionMode(
              TreeSelectionModel.SINGLE_TREE_SELECTION);
      this.setSelectionModel(defaultSelectionModel);

      nodeList = new LinkedList<DefaultMutableTreeNode>();

      foodServiceClient = new FoodServiceClient();
   }

   public void addTopLevelCategory(FoodCategory c) {
      if (c == null) {
         return;
      }

      // Add food category.
      try {
         foodServiceClient.addFoodCategory(c);
      }catch(RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }

      this.addTopLevelCategoryToTree(c);
   }

   public void addTopLevelCategoryToTree(FoodCategory c) {
      if ((c == null) || (c.getId() == null)) {
         return;
      }

      DefaultMutableTreeNode node = new DefaultMutableTreeNode();
      node.setUserObject(new FoodCategoryNode(c));
      model.insertNodeInto(node, rootNode, rootNode.getChildCount());

      // Add added node to node list.
      nodeList.add(node);

      model.reload();
   }

   public void removeTopLevelCategory(FoodCategory c) {
      if ((c==null) || (c.getId()==null)) {
         return;
      }

      List<Food> foodList = null;

      try {
         foodList = foodServiceClient.getFoodListFromFoodCategory(c);
      }catch (RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }

      if ((foodList != null) && (foodList.size() > 0)) {
         JOptionPane.showMessageDialog(this, "此分类下尚存在餐品信息，无法删除此分类",
                 "错误", JOptionPane.ERROR_MESSAGE);
         return;
      }

      this.removeTopLevelCategoryFromTree(c);
      
      try {
         // Remove food category.
         foodServiceClient.removeFoodCategory(c);
      }catch (RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }
   }

   public void removeTopLevelCategoryFromTree(FoodCategory c) {
      if ((c==null) || (c.getId()==null)) {
         return;
      }

      DefaultMutableTreeNode node = this.getTreeNode(c);

      if (node == null) {
         return;
      }

      Object userObject = node.getUserObject();
      if ((userObject == null) && (!(userObject instanceof FoodCategoryNode))) {
         return;
      }

      nodeList.remove(node);
      model.removeNodeFromParent(node);
      model.reload();
   }

   public void updateCategory(FoodCategory c) {
      if ((c==null) || (c.getId()==null)) {
         return;
      }

      try {
         // Update food category.
         foodServiceClient.updateFoodCategory(c);
      }catch (RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }

      this.updateCategoryFromTree(c);
   }

   public void updateCategoryFromTree(FoodCategory c) {
      if ((c==null) || (c.getId()==null)) {
         return;
      }

      DefaultMutableTreeNode node = this.getTreeNode(c);

      if (node == null) {
         return;
      }

      Object userObject = node.getUserObject();
      if ((userObject == null) && (!(userObject instanceof FoodCategoryNode))) {
         return;
      }
      FoodCategoryNode categoryNode = (FoodCategoryNode)userObject;

      categoryNode.setCategory(c);
      model.reload();
   }

   public void clearTree() {
      this.nodeList.clear();
      rootNode.removeAllChildren();
      model.reload();
   }

   public void reloadTree() {
      this.clearTree();

      List<FoodCategory> categoryList = null;

      try {
         categoryList = foodServiceClient.getAllFoodCategories();
      }catch (RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }
      if (categoryList != null) {
         for (FoodCategory category : categoryList) {
            if (category != null) {
               this.addTopLevelCategoryToTree(category);
            }
         }
      }
   }

   public FoodCategory getSelectedFoodCategory() {
      TreePath path = this.getSelectionPath();
      if (path == null) {
         return null;
      }

      Object nodeObject = path.getLastPathComponent();
      if ((nodeObject == null)
              || (!(nodeObject instanceof DefaultMutableTreeNode))) {

         return null;
      }

      DefaultMutableTreeNode node = (DefaultMutableTreeNode)nodeObject;
      Object userObject = node.getUserObject();
      if ((userObject == null) || (!(userObject instanceof FoodCategoryNode))) {
         return null;
      }

      FoodCategoryNode foodCategoryNode = (FoodCategoryNode)userObject;
      return foodCategoryNode.getCategory();
   }

   private DefaultMutableTreeNode getTreeNode(FoodCategory c) {
      if ((c==null) || (c.getId()==null)) {
         return null;
      }
      FoodCategory category = null;
      FoodCategoryNode categoryNode = null;
      Object userObject;
      for (DefaultMutableTreeNode node : nodeList) {
         if (node == null) {
            continue;
         }
         userObject = node.getUserObject();
         if ((userObject == null)
                 || (!(userObject instanceof FoodCategoryNode))) {

            continue;
         }
         categoryNode = (FoodCategoryNode)userObject;
         category = categoryNode.getCategory();
         if (category == null) {
            continue;
         }
         if (c.getId().equals(category.getId())) {
            return node;
         }
      }
      return null;
   }

   private static class FoodCategoryNode {

      private FoodCategory category = null;
      
      public FoodCategoryNode(FoodCategory category) {
         this.category = category;
      }

      public FoodCategory getCategory() {
         return category;
      }

      public void setCategory(FoodCategory category) {
         this.category = category;
      }

      @Override
      public String toString() {
         if (this.getCategory() != null) {
            return category.getName();
         }else {
            return null;
         }
      }
   }
}
