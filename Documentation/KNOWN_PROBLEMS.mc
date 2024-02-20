# Known problems

## Defafult value of select.
When posting a form with a select, if the default-value is not changed, there are no value posted but an empty-string
### Solution
Always have a "select a value" as default and then verify that is itsn't selected.