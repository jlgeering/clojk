# clojk

```
lein figwheel devcards dev
```

open `http://localhost:3449/cards.html` or open `http://localhost:3449/index.html`

## build gh-pages

```
lein cljsbuild once gh-pages
cp resources/public/css/clojk_style.css docs/css/clojk_style.css 
```

check the build locally

```
./bin/serve-docs
```

