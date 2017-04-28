<?php

class Foo {
    public function onMakeGlobalVariablesScript() { // NOK

    }
}

$wgHooks['OnMakeGlobalVariablesScript'] = 'Foo::onMakeGlobalVariablesScript'; // NOK