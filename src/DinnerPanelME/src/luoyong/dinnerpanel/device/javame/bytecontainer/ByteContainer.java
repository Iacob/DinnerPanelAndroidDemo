package luoyong.dinnerpanel.device.javame.bytecontainer;

import java.util.Vector;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class ByteContainer {

   private Vector cellVector = null;

   public ByteContainer() {
      cellVector = new Vector();
   }

   public void addCell(byte[] bytes, int validLength) {
      if ((bytes == null)
              || (validLength < 1)
              || (bytes.length < validLength)) {
         
         return;
      }
      cellVector.addElement(new ByteContainerCell(bytes, validLength));
   }

   public int getValidLength() {
      int result = 0;
      for (int i=0; i<cellVector.size(); i++) {
         result += ((ByteContainerCell)this.cellVector.elementAt(i))
                 .getValidLength();
      }
      return result;
   }

   public byte[] getBytes() {

      int validLength = this.getValidLength();
      
      byte[] result = new byte[validLength];
      
      int lastIndex = 0;
      ByteContainerCell currentCell = null;
      byte[] currentArray = null;
      int currentLength = 0;
      for (int i=0; i<cellVector.size(); i++) {

         currentCell = (ByteContainerCell)this.cellVector.elementAt(i);

         currentArray = currentCell.getBytes();
         currentLength = currentCell.getValidLength();

         for (int cellIndex=0; cellIndex<currentLength; cellIndex++) {
            result[lastIndex + cellIndex] = currentArray[cellIndex];
         }
         lastIndex += currentLength;
      }

      return result;
   }

   private static class ByteContainerCell {

      private byte[] bytes = null;
      private int validLength = 0;

      public ByteContainerCell(byte[] bytes, int validLength) {
         this.bytes = bytes;
         this.validLength = validLength;
      }

      public byte[] getBytes() {
         return this.bytes;
      }

      public int getValidLength() {
         return this.validLength;
      }
   }
}
