import pandas as pd
import joblib
import os

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score, classification_report

# Load dataset
df = pd.read_csv("loan_approval_dataset.csv")

# Normalize column names
df.columns = df.columns.str.strip().str.lower()

print("Initial dataset shape:", df.shape)
print("Columns:", df.columns)

# Drop unnecessary column
df = df.drop(columns=["loan_id"])

# Encode target
df["loan_status"] = df["loan_status"].str.strip().str.lower()
df["loan_status"] = df["loan_status"].map({
    "approved": 1,
    "rejected": 0
})

# Encode categorical features
df = pd.get_dummies(
    df,
    columns=["education", "self_employed"],
    drop_first=True
)

# Remove NaN
df = df.dropna()

print("Dataset shape after cleaning:", df.shape)

# Split features and target
X = df.drop("loan_status", axis=1)
y = df["loan_status"]

# Train-test split
X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.2,
    random_state=42,
    stratify=y
)

# Train model
model = LogisticRegression(max_iter=1000)
model.fit(X_train, y_train)

# Evaluate
y_pred = model.predict(X_test)

print("\nAccuracy:", accuracy_score(y_test, y_pred))
print("\nClassification Report:\n")
print(classification_report(y_test, y_pred))

# Save model
os.makedirs("model", exist_ok=True)
joblib.dump(model, "model/loan_model.pkl")

print("\nModel saved successfully at model/loan_model.pkl")