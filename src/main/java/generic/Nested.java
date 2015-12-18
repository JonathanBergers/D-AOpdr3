package generic;

import java.util.List;

/**
 * Created by jonathan on 18-12-15.
 *
 */
public interface Nested<T> {



   List<T> getChildren();
   T getParent();


   default boolean isRoot(){
      return getParent() == null;
   }


   default boolean isLeaf(){

      if(getChildren() == null){
         return true;
      }
      // extra check
      if(getChildren().isEmpty()){
         return true;
      }
      return false;
   }





}
