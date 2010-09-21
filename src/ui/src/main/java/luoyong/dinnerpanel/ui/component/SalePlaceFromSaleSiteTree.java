package luoyong.dinnerpanel.ui.component;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.rwsclient.SalePlaceServiceClient;
import luoyong.dinnerpanel.rwscommon.info.RWSException;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SalePlaceFromSaleSiteTree extends JTree {

   private DefaultMutableTreeNode rootNode = null;
   private DefaultTreeModel model = null;

   private LinkedList<DefaultMutableTreeNode> nodeList = null;

   private SalePlaceServiceClient salePlaceServiceClient = null;

   public SalePlaceFromSaleSiteTree() {
      super();
      rootNode = new DefaultMutableTreeNode("餐桌列表");
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

      salePlaceServiceClient = new SalePlaceServiceClient();
   }

   public void addTopLevelSalePlace(SalePlace s) {
      if (s == null) {
         return;
      }
      
      // Add sale place.
      try {
         salePlaceServiceClient.addSalePlace(s);
      }catch(RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }

      this.addTopLevelSalePlaceToTree(s);
   }

   public void addTopLevelSalePlaceToTree(SalePlace s) {
      if ((s == null) || (s.getId() == null)) {
         return;
      }

      DefaultMutableTreeNode node = new DefaultMutableTreeNode();
      node.setUserObject(new SalePlaceNode(s));
      model.insertNodeInto(node, rootNode, rootNode.getChildCount());

      // Add added node to node list.
      nodeList.add(node);

      model.reload();
   }

   public void removeTopLevelSalePlace(SalePlace s) {
      if ((s==null) || (s.getId()==null)) {
         return;
      }

      this.removeTopLevelSalePlaceFromTree(s);
      
      // Remove sale place.
      try {
         salePlaceServiceClient.removeSalePlace(s);
      }catch(RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }
   }

   public void removeTopLevelSalePlaceFromTree(SalePlace s) {
      if ((s==null) || (s.getId()==null)) {
         return;
      }

      DefaultMutableTreeNode node = this.getTreeNode(s);

      if (node == null) {
         return;
      }

      Object userObject = node.getUserObject();
      if ((userObject == null) && (!(userObject instanceof SalePlaceNode))) {
         return;
      }
      SalePlaceNode salePlaceNode = (SalePlaceNode)userObject;
      SalePlace salePlace = salePlaceNode.getSalePlace();

      nodeList.remove(node);
      model.removeNodeFromParent(node);
      model.reload();
   }

   public void updateSalePlace(SalePlace s) {
      if ((s==null) || (s.getId()==null)) {
         return;
      }

      // Update sale place.
      try {
         salePlaceServiceClient.updateSalePlace(s);
      }catch(RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }

      this.updateSalePlaceFromTree(s);
   }

   public void updateSalePlaceFromTree(SalePlace s) {
      if ((s==null) || (s.getId()==null)) {
         return;
      }

      DefaultMutableTreeNode node = this.getTreeNode(s);

      if (node == null) {
         return;
      }

      Object userObject = node.getUserObject();
      if ((userObject == null) && (!(userObject instanceof SalePlaceNode))) {
         return;
      }
      SalePlaceNode salePlaceNode = (SalePlaceNode)userObject;

      salePlaceNode.setSalePlace(s);
      model.reload();
   }

   public void clearTree() {
      this.nodeList.clear();
      rootNode.removeAllChildren();
      model.reload();
   }

   public void reloadTree() {
      this.clearTree();

      List<SalePlace> salePlaceList = null;

      try {
         salePlaceList = salePlaceServiceClient.getAllSalePlaces();
      }catch(RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }
      
      if (salePlaceList != null) {
         for (SalePlace salePlace : salePlaceList) {
            if (salePlace != null) {
               this.addTopLevelSalePlaceToTree(salePlace);
            }
         }
      }
   }

   public SalePlace getSelectedSalePlace() {
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
      if ((userObject == null) || (!(userObject instanceof SalePlaceNode))) {
         return null;
      }

      SalePlaceNode salePlaceNode = (SalePlaceNode)userObject;
      return salePlaceNode.getSalePlace();
   }

   private DefaultMutableTreeNode getTreeNode(SalePlace s) {
      if ((s==null) || (s.getId()==null)) {
         return null;
      }
      SalePlace salePlace = null;
      SalePlaceNode salePlaceNode = null;
      Object userObject;
      for (DefaultMutableTreeNode node : nodeList) {
         if (node == null) {
            continue;
         }
         userObject = node.getUserObject();
         if ((userObject == null)
                 || (!(userObject instanceof SalePlaceNode))) {

            continue;
         }
         salePlaceNode = (SalePlaceNode)userObject;
         salePlace = salePlaceNode.getSalePlace();
         if (salePlace == null) {
            continue;
         }
         if (s.getId().equals(salePlace.getId())) {
            return node;
         }
      }
      return null;
   }

   private static class SalePlaceNode {

      private SalePlace salePlace = null;
      
      public SalePlaceNode(SalePlace salePlace) {
         this.salePlace = salePlace;
      }

      public SalePlace getSalePlace() {
         return salePlace;
      }

      public void setSalePlace(SalePlace salePlace) {
         this.salePlace = salePlace;
      }

      @Override
      public String toString() {
         if (this.getSalePlace() != null) {
            return salePlace.getName();
         }else {
            return null;
         }
      }
   }
}
