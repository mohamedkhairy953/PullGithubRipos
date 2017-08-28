# PullGithubRipos
2. Request the GitHub API to show Square's public repositories and parse the JSON
   response.

3. Display a list of repositories, each list item should show
    - repo name
    - description
    - username of the repository owner

4. Request only 10 repos at a time. Use infinite scrolling to load more repos when user reaches end of the list.

5. Cache the repos list locally on the device.

6. Show a light green background if the `fork` flag is false or missing, a white one
   otherwise.
   
7. On a long click on a list item show a dialog to ask if go to repository `html_url` or
   owner `html_url` which is opened then in the browser.

8. Implement swipe-to-refresh, that will clear the cache and request fresh data from the API.
