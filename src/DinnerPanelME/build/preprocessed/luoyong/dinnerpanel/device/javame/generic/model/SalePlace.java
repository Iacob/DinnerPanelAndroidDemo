package luoyong.dinnerpanel.device.javame.generic.model;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SalePlace {

   private String ek;
   
   private Long id;
   
   private String name;
   
   private String description;
   
   private String type;
   
   // private SaleSite saleSite;
   
   private String serviceStatus;
   
   private String status;

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getEk() {
      return ek;
   }

   public void setEk(String ek) {
      this.ek = ek;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getServiceStatus() {
      return serviceStatus;
   }

   public void setServiceStatus(String serviceStatus) {
      this.serviceStatus = serviceStatus;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }
}
