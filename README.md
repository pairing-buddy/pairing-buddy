![Build Status](https://circleci.com/gh/pairing-buddy/pairing-buddy/tree/master.png?circle-token=b1d95d08587f772be8151f4a9d4605192690e472)

# pairing-buddy

## Development

1. Checkout project
2. Start Scala JS workbench watcher:
   ```bash
   sbt ~re-start
   ```
3. Access site:
    [http://0.0.0.0:8082/](http://0.0.0.0:8082/)

## Deployment

Project is containerised and deployed automatically once build on master branch is succeeded.

1. Container is built and pushed to [docker hub](https://hub.docker.com/r/asarturas/pairing-buddy/).
2. Built container is deployed to GCE.

Current production url is [http://pairing-buddy.elecode.com](http://pairing-buddy.elecode.com) 

Note that at this stage containers are not versioned,
 which means that to revert to previous version
 you need to go to [circleci](https://circleci.com/gh/pairing-buddy/pairing-buddy)
 and rerun desired build.