# Overview

A plugin that adds a custom REST api endpoint to confluence (/rgsearch/search/1.0).
Calls to /rgsearch/search/1.0 return results from a local elasticsearch index.

# How to compile

* Use the Atlassian Plugin SDK (https://developer.atlassian.com/server/framework/atlassian-sdk/downloads/)
* Run `atlas-package`

# Useful commands

You have successfully created an Atlassian Plugin!

Here are the SDK commands you'll use immediately:

```
* atlas-run   -- installs this plugin into the product and starts it on localhost
* atlas-debug -- same as atlas-run, but allows a debugger to attach at port 5005
* atlas-cli   -- after atlas-run or atlas-debug, opens a Maven command line window:
                 - 'pi' reinstalls the plugin into the running product instance
* atlas-help  -- prints description for all commands in the SDK
```

Full documentation is always available at:

https://developer.atlassian.com/display/DOCS/Introduction+to+the+Atlassian+Plugin+SDK

# Useful if using idea
http://biercoff.com/how-to-setup-jira-plugin-project-in-intelij-idea-on-mac/
