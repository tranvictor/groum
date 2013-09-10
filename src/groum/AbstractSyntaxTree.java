package groum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

public class AbstractSyntaxTree {
	private CompilationUnit ast;
	
	protected AbstractSyntaxTree(String filename) {
		File file = new File(filename);
		try {
			parseFile(file);
		} catch (IOException e) {
			System.out.printf("File \"%s\" doesn't exist or cannot be read!\n", filename);
		}
	}
	
	protected void parseFile(File file) throws IOException{
		String source = FileUtils.readFileToString(file);
	    this.parseSource(source);
	}
	
	protected void parseSource(String source){
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(source.toCharArray());
		// In order to parse 1.5 code, some compiler options need to be set to 1.5
		@SuppressWarnings("rawtypes")
		Map options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_5, options);
		parser.setCompilerOptions(options);
		ast = (CompilationUnit) parser.createAST(null);
	}
	
	protected CompilationUnit getAST(){
		return ast;
	}
	
	protected ASTNode getRoot(){
		return ast.getRoot();
	}
	
	@SuppressWarnings("unchecked")
	protected Object[] getChildren(ASTNode node) {
	    List<StructuralPropertyDescriptor> list= node.structuralPropertiesForType();
	    List<Object> children = new ArrayList<Object>();
	    for (int i= 0; i < list.size(); i++) {
	        StructuralPropertyDescriptor curr= list.get(i);
	            Object child= node.getStructuralProperty(curr);
	        if (child instanceof List) {
	                children.add((List<ASTNode>) child);
	        } else if (child instanceof ASTNode) {
	            children.add(child);
	        }
	    }
	    return children.toArray();
	}
}
