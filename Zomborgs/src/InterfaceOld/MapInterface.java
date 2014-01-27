/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceOld;

import java.util.List;
import mapObjects.MapObject;

/**
 *
 * @author Dariusz
 */
public class MapInterface extends Interface {

    public MapInterface() {
    }
    public void displayMap(List<List<MapObject>> theMap) {
        
        for(int i=0; i < theMap.size(); i++) {
            for(int j=0; j < theMap.get(i).size(); j++) {
                System.out.println(theMap.get(i).get(j).getSmbol());
            }
            System.out.println("\n");
        }
    }
}
