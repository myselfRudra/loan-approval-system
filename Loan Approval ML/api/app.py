from fastapi import FastAPI
from pydantic import BaseModel
import joblib
import numpy as np
import os

app = FastAPI(title="Loan Approval ML Service")

MODEL_PATH = os.path.join("..", "model", "loan_model.pkl")
model = joblib.load(MODEL_PATH)


class LoanRequest(BaseModel):
    no_of_dependents: int
    income_annum: int
    loan_amount: int
    loan_term: int
    cibil_score: int
    residential_assets_value: int
    commercial_assets_value: int
    luxury_assets_value: int
    bank_asset_value: int
    education_graduate: int
    self_employed_yes: int


@app.post("/predict-loan")
def predict_loan(data: LoanRequest):

    input_array = np.array([[
        data.no_of_dependents,
        data.income_annum,
        data.loan_amount,
        data.loan_term,
        data.cibil_score,
        data.residential_assets_value,
        data.commercial_assets_value,
        data.luxury_assets_value,
        data.bank_asset_value,
        data.education_graduate,
        data.self_employed_yes
    ]])

    probability = model.predict_proba(input_array)[0][1]
    decision = "APPROVED" if probability >= 0.5 else "REJECTED"

    return {
        "decision": decision,
        "risk_score": round(1 - probability, 2)
    }

