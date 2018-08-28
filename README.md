# district-ui-mobile

*Alpha, Currently in Development*

[![Build Status](https://travis-ci.org/district0x/district-ui-mobile.svg?branch=master)](https://travis-ci.org/district0x/district-ui-mobile)

Clojurescript
[re-mount](https://github.com/district0x/d0x-INFRA/blob/master/re-mount.md)
module, that provides mobile integration.

## Installation
Add `[district0x/district-ui-mobile "0.1.0-SNAPSHOT"]` into your
project.clj

## Quickstart

`district.ui.mobile` first needs to be initialized, as defined in the
[re-mount](https://github.com/district0x/d0x-INFRA/blob/master/re-mount.md)
pattern.

Include `district.ui.mobile` within the main file where you call `mount/start`

```clojure
;; core.cljs, or main.cljs, etc...
(require '[mount.core :as mount])
(require '[district.ui.mobile])

;; optional initialization options
(def district-ui-options
  {:mobile {:force-mobile-device false}})

(mount/start (with-args district-ui-options))
```

Subscriptions can then be made to determine if the web application is
being viewed from mobile devices, specifically Android or iOS devices.

```clojure
;; re-frame view file
(require '[re-frame :refer [subscribe]])
(require '[district.ui.mobile.subs :as mobile-subs])

(defn show-device []
  (let [android? (subscribe [::mobile-subs/android?])
        ios? (subscribe [::mobile-subs/ios?])]
    (fn []
     [:div
      (cond
       @android? [:span "You are on an Android device."]
       @ios? [:span "You are on an iOS (iPhone, iPad) device."]
       :else [:span "We don't know if you're on a mobile device."])])))
```

Another use-case is determining if the device is
[coinbase](https://wallet.coinbase.com/)-compatible.

```clojure
;; re-frame view file
(require '[re-frame :refer [subscribe]])
(require '[district.ui.mobile.subs :as mobile-subs])

(defn coinbase-dialog []
  (let [coinbase-compatible? (subscribe [::mobile-subs/coinbase-compatible?])]
    (fn []
     [:div
      (if @coinbase-compatible?
        [:span "Mobile device is coinbase compatible."]
        [:span "Device is not coinbase compatible."])])))
```

## API Overview

- [district.ui.mobile](#districtuimobile)
- [district.ui.mobile.subs](#districtuimobilesubs)
  - [::android?](#android-)
  - [::ios?](#ios-)
  - [::coinbase-compatible?](#coinbase-compatible-)

## district.ui.mobile
This namespace contains the `mobile`
[mount](https://github.com/tolitius/mount) module.

You can pass the following args while initiating this module:
* `:force-mobile-device` Used to imitate a mobile browser. Accepts one
  of the values: `true` (android), `:android`, `:ios`, or `false`. By
  default, this value is set to `false`.

For example, if I wanted to imitate an iOS device:

```clojure
(ns my-district.core
 (:require [mount.core :as mount]
           [district.ui.mobile]
           ;;...
           ))

(-> (mount/with-args
     {:mobile {:force-mobile-device :ios}
      ;; Additional mount options...
      })
    (mount/start))
```

## district.ui.mobile.subs
re-frame subscriptions provided by this module:

#### `::android? []`
Returns `true` if an Android mobile device is viewing the web
application, otherwise `false`

#### `::ios? []`
Returns `true` if an iOS mobile device (iPhone, iPad) is viewing the
web application, otherwise `false`.

#### `::coinbase-compatible? []`
Returns `true` if an coinbase-compatible mobile device is viewing the
web application, otherwise `false`.

## Development

### Testing
```bash
$ lein doo
```
