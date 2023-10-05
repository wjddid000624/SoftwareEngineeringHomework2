package edu.yonsei.csi3106.homework2;

/**
 * The Element class is the superclass of both Book and Collection. 
 * It contains a reference to the parent collection 
 * that directly contains this element. 
 * 
 * An element can directly belong to at most one parent collection, 
 * but that collection can belong to other collections, 
 * so an element can indirectly belong to several collections.
 * 
 * It is optional for your Library code to enforce that no collection 
 * can (even indirectly) belong to itself, i.e., the graph of connections
 *  along parentCollection should be acyclic.
 *  
 * You should not modify the Element class. 
 * Its two methods have been already implemented.
 */
public abstract class Element {
    private Collection parentCollection;
    
    /**
     * Get the parent collection of this element.
     *
     * @return parent collection
     */
    public Collection getParentCollection() {
        return parentCollection;
    }

    /**
     * Set the parent collection for this element.
     *
     * @param collection collection to be set as parent
     */
    public void setParentCollection(Collection collection) {
        parentCollection = collection;
    }
}