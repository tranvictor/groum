package groum;

public class SimpleGroum {
	private AbstractSyntaxTree ast;
	private GroumNode root;
	
	protected SimpleGroum(AbstractSyntaxTree ast){
		this.ast = ast;
		this.root = new GroumNode(this.ast.getRoot(), null);
		this.constructGroum(this.root);
	}
	
	private void constructGroum(GroumNode r){
		this.root.travel();
	}
}
