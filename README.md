## clj-audiotagger

[![Clojars Project](https://img.shields.io/clojars/v/clj-audiotagger.svg)](https://clojars.org/clj-audiotagger)

Clojure library designed to make it easy to manipulate/cleanup bad mp3 tag with UTF-8 support.

Design with simplicity in mind so out of the box it can do the following:

- Set the file name as title of the mp3 track
- Set the track index to current position in the list e.g. track/track-total based on the current position
- Set common tags shared by all the files (e.g. same artist, album, genre, etc)

Built on top of the two useful library

- [claudio][] (Clojure)
- [jaudiotagger][] (Java)

## Basic Usage

Will be provided the easy way to run this later for now please see below.

## Development

Available via [clojars.org/clj-audiotagger](https://clojars.org/clj-audiotagger)

- Leiningen

```clojure
[clj-audiotagger "0.1.0-SNAPSHOT"]
```

- Gradle

```
compile "clj-audiotagger:clj-audiotagger:0.1.0-SNAPSHOT"
```

- Maven

```xml
<dependency>
  <groupId>clj-audiotagger</groupId>
  <artifactId>clj-audiotagger</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

### Pre-requisite

- Java SDK
- Clojure
- Leiningen

### Build and run locally

- Clone the repository

```sh
# clone the repository
mkdir -p ~/projects

# clone the repository
git clone git@github.com:agilecreativity/clj-audiotagger.git ~/projects
```

- Get some help

Run the following to get some help

```sh
lein run -- --help`
```

Which should produce something like

```
Update mp3 id3 tag using simple rules.

Usage: clj-audiotagger [options]
  -b, --base-dir DIR             .  The staring directory
  -f, --file-name-as-title          Use file name as title
  -t, --position-as-track-order     Use position as track order
  -u, --set-shared-tags             Update shared tags
  -h, --help

Options:

--base-dir DIR starting directory
--filename-as-title
--position-as-track-order
--set-shared-tags tags
  title   TITLE
  album   ALBUM
  artist  ARTIST
  genre   GENRE
  year    YEAR
  comment COMMENT

Please refer to the manual page for more information.
```

### Sample Usage

- To rename all the mp3 files to the filename try

```sh
lein run -- --base-dir ~/media/mp3s --file-name-as-title
```

- To update the play index try

```sh
# long version
lein run -- --base-dir ~/media/mp3s --position-as-track-order

# Or short version
lein run -- --base-dir ~/media/mp3s -t
```

- To update the common attributes of a given list of files

e.g. Good use-case if have the media sharing the same type of attribute (artist, album, year)

```sh
# Using short version of the command
lein run -- -b ~/media/mp3s -u album "Metallica Greatest Hits" artist "Metallica" comment "My fav band"
```

for list of the attribute please see [claudio][] for attribute you can set.

The common values are:

```
title   TITLE
album   ALBUM
artist  ARTIST
genre   GENRE
year    YEAR
comment COMMENT
```

## TODO

- Add documentation on how to run it with [lien-bin][]
- Add the ability to update of cover image for a file

## Useful Links

Some links I found useful while developing this library

- [claudio][] - read/write MP3 ID3 tags with Clojure
- [jaudiotagger][] - most recent version of the original Java library
- [fs][] - file system library by [Anthony Grimes](https://github.com/Raynes)
- [Fleetdb project](https://github.com/mmcgrana/fleetdb/blob/master/project.clj)
- [Mixing Java and Clojure project](http://hypirion.com/musings/advanced-intermixing-java-clj)
- [Good example on Java interop with Clojure](http://www.braveclojure.com/java/)
- [Working with file in Clojure](http://clojure-doc.org/articles/cookbooks/files_and_directories.html)
- [Java interop with Clojure](http://www.braveclojure.com/java/)

## License

Copyright © 2016 Burin Choomnuan

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[claudio]: https://github.com/pandeiro/claudio
[jaudiotagger]: https://bitbucket.org/ijabz/jaudiotagger/src
[fs]: https://github.com/Raynes/fs
[lein-bin]: https://github.com/Raynes/lein-bin
