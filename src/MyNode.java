import java.util.ArrayList;
import java.util.List;

public class MyNode<T> {
		//data
		private T Data;
		private MyNode<T> Parent;
		private List<MyNode<T>> ChildList;
		
		//constructor
		public MyNode( T RootData){
			this.Data = RootData;
			this.Parent = null;
			this.ChildList = new ArrayList<MyNode<T>>();
		}
		
		//method
		public MyNode<T> getParent(){
			return( Parent);
		};
		
		public List<MyNode<T>> children()
		{
			return( ChildList);
		};
		
		
		public MyNode<T> addChild( T CData ){
			//define a new node
			MyNode<T> child = new MyNode<T>( CData );
			//its parent point to this
			child.Parent = this;
			//add to childlist
			this.ChildList.add(child);
			
			return(child);
		};
		//find a node with data match Input
		public MyNode<T> findNode( T Input )
		{
			MyNode<T> node = null;
			if(this.Data.equals( Input) )
			{
				node = this;
			}
			else if( this.ChildList.isEmpty()){
				node = null;
			}
			else
			{
				for( MyNode<T> n : this.ChildList)
				{
					node = n.findNode(Input);
					//if found, return
					if( node != null )
						break;
				}
			}
			
			return(node);
		};
		
		public void RemoveChild()
		{
			//find parent
			MyNode<T> pnode = this.Parent;
			//remove from parent's childlist
			pnode.ChildList.remove(this);
			
		};
		
		public int Traversal()
		{
			int num = 1;
			
			if( !this.ChildList.isEmpty())
			{
				for( MyNode<T> node: this.ChildList)
				{
					num += node.Traversal();
				};
			};
			return(num);
		};

}
