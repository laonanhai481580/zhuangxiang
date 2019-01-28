package qis_disable;

import update.UpdateCcmByJdbc830;
import update.UpdateClByJdbc830;
import update.UpdateFpmByJdbc830;
import update.UpdateLcmByJdbc830;
import update.UpdateSqByJdbc830;
import update.UpdateTpByJdbc830;


public class UpdateAllBusiness {
	public static void main(String[] args) {
		UpdateClByJdbc830.diableNum();
		UpdateCcmByJdbc830.diableNum();
		UpdateFpmByJdbc830.diableNum();
		UpdateLcmByJdbc830.diableNum();
		UpdateSqByJdbc830.diableNum();
		UpdateTpByJdbc830.diableNum();
	}
}
