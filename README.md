# clojk

```
lein figwheel devcards dev
```

open `http://localhost:3449/cards.html` or open `http://localhost:3449/index.html`

## build css

see https://github.com/tuhlmann/lein-sass

```
brew install sassc
lein sass once
```

watch changes:
```
lein sass auto
```


## build docs (gh-pages)

```
lein build-docs
cp resources/public/css/clojk_style.css docs/css/clojk_style.css 
```

check the build locally

```
env PORT=8080 ./bin/serve-docs
```

