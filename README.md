# magicscraper

Card database must be manually filled by going into repl and doing the following:

    lein repl
    (use 'magicscraper.repl)
    (start-server) ;; initiates db
    (use 'magicscraper.models.db)
    (populate-cards)

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2014 FIXME
