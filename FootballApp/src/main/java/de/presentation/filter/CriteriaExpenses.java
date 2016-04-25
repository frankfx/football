package de.presentation.filter;

import java.util.ArrayList;
import java.util.List;

import de.business.TipicoModel;
import de.utils.math.ExpressionType;

public class CriteriaExpenses implements ICriteria {

	private float mThreshold = 5.0f;
	private ExpressionType mOperator;

	public CriteriaExpenses(float pThreshold, ExpressionType pOperator) {
		mThreshold = pThreshold;
		mOperator = pOperator;
	}

	@Override
	public List<TipicoModel> matchedCriteria(List<TipicoModel> pModelList) {
		List<TipicoModel> lResult = new ArrayList<TipicoModel>();

		for (TipicoModel lModel : pModelList) {
			switch (mOperator) {
			case LOWER:
				if (lModel.getExpenses() < mThreshold)
					lResult.add(lModel);
				break;
			case GREATER:
				if (lModel.getExpenses() > mThreshold)
					lResult.add(lModel);
				break;
			default:
				break;
			}
		}
		return lResult;
	}
}