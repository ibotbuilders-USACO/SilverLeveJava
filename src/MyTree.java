import java.util.List;


public class MyTree<T>{
	//root 
	private MyNode<T> root;
	
	//constructor
	MyTree()
	{
		this.root = null;
	}
	MyTree( MyNode<T> r)
	{
		this.root = r;
	};
	
	MyTree( T Data )
	{
		MyNode<T> r = new MyNode<T>( Data );
		this.root = r;
	};
	
	//method
	
	//add a new data
	public MyNode<T> addChild( T Data, T Pdata )
	{
		MyNode<T> node, pnode;
		if(this.root == null)
		{
			node = new MyNode<T>( Data );
			this.root = node;
		}
		else{
			//if data is already in tree skip
			node = this.root.findNode(Data);
		
			{
				// find if pdata is in tree
				//find node match parent data
				pnode = this.root.findNode( Pdata );
				//if there is a match on pdata, add the data to parent node
				if(pnode != null)
				{
					if( node == null )
						node = pnode.addChild( Data );
				}
				else
				{
					// if pdata is not in tree, return a null
			    	node = pnode;
				};
			};
		}
		
		return( node );
	}
	
	public int Traversal()
	{
		int num = 0;
		num = this.root.Traversal();
		return(num);
	}
}
