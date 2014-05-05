# magicscraper

Card database must be manually filled by going into repl and doing the following:

    lein repl
    (use 'magicscraper.repl)
    (start-server)
    (use 'magicscraper.models.db)
    (populate-sets)
    (populate-cards)

This will fill in card details for Theros, Born of the gods and Journey into nyx.

# TODOS

The sets table is not currently used. I have hard coded the card population on the three above mentioned sets. 
I am next going to populate cards within all the sets that is currently searchable on wizards.com. 
This will produce some identical card names in different sets, 
but I plan to create a view or search feature where each card name is only displayed once 
and by clicking on it will display the card details and all the sets its featured in. 
In addition to the pictures this will lay the foundation for making a search and add function,
combined with a collection that makes it possible to add a card to a collection.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2014 FIXME
