RESTful api in support of result-tracker

This is the first time I have used GCP Firestore. It is not much different than the previous generation datastore.

I used a nested structure for the data objects. It has drawbacks, but other benefits. In the past I have designed my API's to with a single resource structure, the nesting here presented some challenges, but overall works great with the nested documents that are native to Firestore.

[result tracker](https://result-tracker.appspot.com/)
