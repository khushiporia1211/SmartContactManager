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

function setContactValue(field, value) {
    document.getElementById(`contact_${field}`).textContent =
        value === null || value === undefined || value === "" ? "Not provided" : String(value);
}

function setContactLink(field, value) {
    const container = document.getElementById(`contact_${field}`);
    container.replaceChildren();

    if (!value) {
        container.textContent = "Not provided";
        return;
    }

    const text = String(value);
    const link = document.createElement("a");
    link.textContent = text;
    link.className = "text-blue-700 underline";
    link.target = "_blank";
    link.rel = "noopener noreferrer";

    try {
        const url = new URL(text, window.location.origin);
        if (url.protocol !== "http:" && url.protocol !== "https:") {
            container.textContent = text;
            return;
        }
        link.href = url.href;
        container.append(link);
    } catch (error) {
        container.textContent = text;
    }
}

function setContactPicture(value) {
    setContactValue("picture", value);
    const image = document.getElementById("contact_picture_image");
    image.classList.add("hidden");
    image.removeAttribute("src");

    if (typeof value !== "string" || !value) {
        return;
    }

    try {
        const url = new URL(value, window.location.origin);
        if (url.protocol === "http:" || url.protocol === "https:") {
            image.src = url.href;
            image.classList.remove("hidden");
        }
    } catch (error) {
        // Keep the picture URL visible as text when it cannot be used as an image source.
    }
}

function setContactLinks(links) {
    const container = document.getElementById("contact_links");
    container.replaceChildren();

    if (!Array.isArray(links) || links.length === 0) {
        container.textContent = "Not provided";
        return;
    }

    const list = document.createElement("ul");
    list.className = "list-disc space-y-1 pl-5";

    links.forEach((item) => {
        const listItem = document.createElement("li");
        const title = item.title || item.link || "Link";
        const link = document.createElement("a");
        link.textContent = String(title);
        link.className = "text-blue-700 underline";

        try {
            const url = new URL(item.link, window.location.origin);
            if (url.protocol === "http:" || url.protocol === "https:") {
                link.href = url.href;
                link.target = "_blank";
                link.rel = "noopener noreferrer";
                listItem.append(link);
            } else {
                listItem.textContent = String(title);
            }
        } catch (error) {
            listItem.textContent = String(title);
        }

        list.append(listItem);
    });

    container.append(list);
}

async function loadContactData(id) {
    try {
        const response = await fetch(`/api/contacts/${encodeURIComponent(id)}`);
        if (!response.ok) {
            throw new Error(`Unable to load contact (${response.status} ${response.statusText})`);
        }

        const data = await response.json();
        document.getElementById("contact_name").textContent = data.name || "Contact details";
        document.getElementById("contact_email").textContent =
            data.email === null || data.email === undefined || data.email === "" ? "" : String(data.email);

        setContactValue("id", data.id);
        setContactValue("name_value", data.name);
        setContactValue("email_value", data.email);
        setContactValue("phoneNumber", data.phoneNumber);
        setContactValue("address", data.address);
        setContactPicture(data.picture);
        setContactValue("description", data.description);
        setContactValue("favourite", typeof data.favourite === "boolean" ? (data.favourite ? "Yes" : "No") : data.favourite);
        setContactLink("websiteLink", data.websiteLink);
        setContactLink("linkedInLink", data.linkedInLink);
        setContactValue("cloundinaryImagePublicId", data.cloundinaryImagePublicId);
        setContactLinks(data.links);

        openContactModel();
    } catch (error) {
        console.error("Unable to load contact details:", error);
    }
}