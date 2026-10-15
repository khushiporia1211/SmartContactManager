console.log("Contacts.js");
const viewContactModel = document.getElementById("view_contact_model");

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_model',
  override: true
};

const contactModel=new Modal(viewContactModel,options,instanceOptions);

function openContactModel(){
    contactModel.show();
}

function closeContactModel(){
    contactModel.hide();
}