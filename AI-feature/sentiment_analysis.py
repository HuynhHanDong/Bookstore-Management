from fastapi import FastAPI
from pydantic import BaseModel
from textblob import TextBlob
import re

app = FastAPI()

class Review(BaseModel):
    text: str

def extract_reason(blob: TextBlob) -> str:
    # Get top adjectives and adverbs
    pos_words = [word for word, tag in blob.tags if tag in ("JJ", "RB")]
    if not pos_words:
        return "The sentiment is inferred from overall tone."
    top_words = ", ".join(pos_words[:3])
    return f"The sentiment is influenced by words like: {top_words}."

@app.post("/analyze")
def analyze_review(review: Review):
    blob = TextBlob(review.text)
    polarity = blob.sentiment.polarity

    if polarity > 0.1:
        sentiment = "positive"
    elif polarity < -0.1:
        sentiment = "negative"
    else:
        sentiment = "neutral"

    reason = extract_reason(blob)

    return {
        "sentiment": sentiment,
        "polarity": polarity,
        "reason": reason
    }
