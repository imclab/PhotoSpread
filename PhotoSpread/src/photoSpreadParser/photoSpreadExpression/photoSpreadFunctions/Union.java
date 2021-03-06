/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package photoSpreadParser.photoSpreadExpression.photoSpreadFunctions;


import photoSpread.PhotoSpreadException;
import photoSpread.PhotoSpreadException.FormulaError;
import photoSpreadObjects.PhotoSpreadObject;
import photoSpreadParser.photoSpreadExpression.PhotoSpreadEvaluatable;
import photoSpreadParser.photoSpreadExpression.PhotoSpreadFormulaExpression;
import photoSpreadTable.PhotoSpreadCell;
import photoSpreadUtilities.PhotoSpreadObjIndexerFinder;
import photoSpreadUtilities.TreeSetRandomSubsetIterable;

/**
 *
 * @author skandel
 */
public class Union<A extends PhotoSpreadFormulaExpression> extends PhotoSpreadFunction
implements PhotoSpreadEvaluatable {

	public Union() {
		this("Union");
	}

	public Union(String _functionName) {
        super(_functionName);
    }
    
	@Override
	public TreeSetRandomSubsetIterable<PhotoSpreadObject> valueOf() 
	throws FormulaError {
		
		AllArgEvalResults computedArgs;
		TreeSetRandomSubsetIterable<PhotoSpreadObject> res =
			new TreeSetRandomSubsetIterable<PhotoSpreadObject>();
			res.setIndexer(new PhotoSpreadObjIndexerFinder());

		
		// Have all the arguments to this call to 'union'
		// computed. We may get multiple results for each
		// argument. This conceptual ArrayList<ArrayList<PhotoSpreadxxx>>
		// is encapsulated in the ArgEvalResults class.
		// The valueOfArgs() method returns one of that
		// class' instances, filled with results:
		
		computedArgs = valueOfArgs();

		// Now just Union everything together:

		AllArgEvalResults.FlattenedArgsIterator argResultsIt =
			computedArgs.flattenedArgsIterator();
		
		while (argResultsIt.hasNext()) {
			try {
				// Get another result:
				PhotoSpreadObject argRes = argResultsIt.next();
				// Arg results are allowed to be empty sets,
				// in which case AllArgEvalResults.FlattenedArgsIterator
				// returns a null. Skip such empty results:
				if (argRes == null) continue;
				res.add(argRes);

			} catch (ClassCastException e) {
				throw new PhotoSpreadException.FormulaError(
						"In function '" +
						getFunctionName() +
						"' the argument '" +
						argResultsIt.getMostRecentlyFedOut() +
						"' cannot be converted to an object.");
			}
		}
		return res;
	}

	@Override
	public TreeSetRandomSubsetIterable<PhotoSpreadObject> evaluate(
			PhotoSpreadCell cell) throws FormulaError {
		return valueOf();
	}
}
